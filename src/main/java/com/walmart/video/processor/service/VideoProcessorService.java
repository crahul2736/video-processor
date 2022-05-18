package com.walmart.video.processor.service;

import com.walmart.video.processor.model.ImageAnalyzeRes;
import com.walmart.video.processor.model.Object;
import com.walmart.video.processor.model.ProductDetails;
import com.walmart.video.processor.model.Response;
import com.walmart.video.processor.utils.DirUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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

    public Response process() throws Exception {

        //Dir cleanup
        DirUtil.clearDir(imgCroppedPath);
        DirUtil.clearDir(imgScannedPath);

        List<String> imgDetails = new ArrayList<>();
        List<ImageAnalyzeRes> imageAnalyzeResLst = new ArrayList<>();
        List<ImageAnalyzeRes> croppedImageAnalyzeResLst = new ArrayList<>();
        List<Path> pathList;

        //Get images from video
        String imgPath = videoToImageConverter.convertVideoToImage();
        pathList = DirUtil.getFilePaths(imgPath);

        //Analyze Framed Image
        pathList.forEach(System.out::println);
        for (Path path : pathList) {
            imageAnalyzeResLst.add(analyzeLocalImage.analyzeImage(path));
        }
        log.info(imageAnalyzeResLst.toString());

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

    public Response processResponse(List<ImageAnalyzeRes> croppedImageAnalyzeResLst) {

        List<ProductDetails> productDetailsLst = new ArrayList<>();
        for (ImageAnalyzeRes imageAnalyzeRes : croppedImageAnalyzeResLst) {
            ProductDetails productDetails = ProductDetails.builder().productName(imageAnalyzeRes.getProductName())
                    .brand(imageAnalyzeRes.getBrands().size() > 0 ? imageAnalyzeRes.getBrands().get(0).getName() : null)
                    .dominantColor(imageAnalyzeRes.getColor().getDominantColors().get(0))
                    .imgUrl("")
                    .tag(imageAnalyzeRes.getTags().get(0).getName())
                    .confidence(imageAnalyzeRes.getTags().get(0).getConfidence())
                    .build();

            productDetailsLst.add(productDetails);
        }
        Response response = new Response();
        response.setProductDetails(productDetailsLst);

        return response;
    }


}
