package com.walmart.video.processor.service;

import com.walmart.video.processor.model.ImageAnalyzeRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class VideoProcessorService {

    @Autowired
    AnalyzeLocalImage analyzeLocalImage;

    @Autowired
    VideoToImageConverter videoToImageConverter;

    public List<ImageAnalyzeRes> process() throws Exception {
        List<String> imgDetails = new ArrayList<>();
        List<ImageAnalyzeRes> imageAnalyzeResLst = new ArrayList<>();
        List<Path> pathList;
        //Get images from video
        String imgPath = videoToImageConverter.convertVideoToImage();
        try (Stream<Path> stream = Files.walk(Paths.get(imgPath))) {
            pathList = stream.map(Path::normalize)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
        pathList.forEach(System.out::println);

        //Analyze each Image
        for (Path path : pathList) {
//            imgDetails.add(analyzeLocalImage.analyzeImage(path));
            imageAnalyzeResLst.add(analyzeLocalImage.analyzeImage(path));
        }
        log.info(imageAnalyzeResLst.toString());
        return imageAnalyzeResLst;
    }
}
