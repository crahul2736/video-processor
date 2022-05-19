package com.walmart.video.processor.service;

import com.walmart.video.processor.model.ImageAnalyzeRes;
import com.walmart.video.processor.model.Object;
import com.walmart.video.processor.model.ProductDetails;
import com.walmart.video.processor.model.Response;
import com.walmart.video.processor.utils.DirUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VideoProcessorService {

    @Autowired
    AnalyzeLocalImage analyzeLocalImage;

    @Autowired
    VideoToImageConverter videoToImageConverter;

    @Autowired
    ImageCropperService imageCropperService;

    @Value("${img.scanned.path}")
    String imgScannedPath;

    @Value("${img.cropped.path}")
    String imgCroppedPath;

    @Value("${video.upload.path}")
    String videoUploadPath;

    public Response process(String fileName) throws Exception {

        //Dir cleanup
        DirUtil.clearDir(imgCroppedPath);
        DirUtil.clearDir(imgScannedPath);

        List<ImageAnalyzeRes> imageAnalyzeResLst = new ArrayList<>();
        List<ImageAnalyzeRes> croppedImageAnalyzeResLst = new ArrayList<>();
        List<Path> pathList;

        //Get images from video
        String imgPath = videoToImageConverter.convertVideoToImage(videoUploadPath + fileName);
        pathList = DirUtil.getFilePaths(imgPath);

        //Analyze Framed Image
        pathList.forEach(System.out::println);
        for (Path path : pathList) {
            imageAnalyzeResLst.add(analyzeLocalImage.analyzeImage(path));
        }
        log.info(imageAnalyzeResLst.toString());

        //Crop Images
        for (ImageAnalyzeRes imageAnalyzeRes : imageAnalyzeResLst) {
            for (Object object : imageAnalyzeRes.getObjects()) {
                imageCropperService.cropImages(imageAnalyzeRes.getImgPath(), imgCroppedPath, object.getObject(), object.getRectangle().getX(), object.getRectangle().getY(),
                        object.getRectangle().getW(), object.getRectangle().getH());
            }
        }

        //Analyze Cropped Images
        pathList = DirUtil.getFilePaths(imgCroppedPath);
        pathList.forEach(System.out::println);
        for (Path path : pathList) {
            croppedImageAnalyzeResLst.add(analyzeLocalImage.analyzeImage(path));
        }


        return processResponse(croppedImageAnalyzeResLst);
    }

    /**
     * Store uploaded video
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String saveVideo(MultipartFile file) throws IOException {
        DirUtil.clearDir(videoUploadPath);
        log.info("Upload initiated");
        String fileName = String.format("%s.%s", "vid-" + System.currentTimeMillis(),
                FilenameUtils.getExtension(file.getOriginalFilename()));

        File dir = new File(videoUploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String location = new StringBuilder(videoUploadPath).append(File.separator).append(fileName).toString();
        Path path = Paths.get(location);
        Files.write(path, file.getBytes());
        log.info("Upload completed to path : {}", path.toAbsolutePath());
        return path.getFileName().toString();
    }

    public Response processResponse(List<ImageAnalyzeRes> croppedImageAnalyzeResLst) {

        List<ProductDetails> productDetailsLst = new ArrayList<>();
        for (ImageAnalyzeRes imageAnalyzeRes : croppedImageAnalyzeResLst) {
            ProductDetails productDetails = ProductDetails.builder().productName(imageAnalyzeRes.getProductName())
                    .brand(imageAnalyzeRes.getBrands().size() > 0 ? imageAnalyzeRes.getBrands().get(0).getName() : null)
                    .dominantColor(imageAnalyzeRes.getColor().getDominantColors().get(0))
                    .imgUrl(imageAnalyzeRes.getImgUrl())
                    .tag(imageAnalyzeRes.getTags().get(0).getName())
                    .confidence(imageAnalyzeRes.getTags().get(0).getConfidence())
                    .build();

            productDetailsLst.add(productDetails);
        }
        Response response = new Response();
        List<ProductDetails> uniqueProducts = productDetailsLst.stream()
                .collect(Collectors.groupingBy(ProductDetails::getTag,
                        Collectors.maxBy(Comparator.comparing(ProductDetails::getConfidence))))
                .values()
                .stream()
                .map(Optional::get)
                .collect(Collectors.toList());
        response.setProductDetails(uniqueProducts);

        return response;
    }
}
