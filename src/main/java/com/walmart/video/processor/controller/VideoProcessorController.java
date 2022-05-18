package com.walmart.video.processor.controller;

import com.walmart.video.processor.model.ImageAnalyzeRes;
import com.walmart.video.processor.service.VideoProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class VideoProcessorController {

    @Autowired
    VideoProcessorService videoProcessorService;

    @GetMapping("upload")
    public ResponseEntity upload() throws Exception {
        long sTime = System.currentTimeMillis();
        List<ImageAnalyzeRes> imgData = videoProcessorService.process();
        log.info("Total execution time: {}", System.currentTimeMillis() - sTime);
        return new ResponseEntity(imgData, HttpStatus.OK);
    }
}
