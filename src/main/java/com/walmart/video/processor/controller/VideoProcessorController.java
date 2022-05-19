package com.walmart.video.processor.controller;

import com.walmart.video.processor.model.Response;
import com.walmart.video.processor.service.VideoProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/video/")
public class VideoProcessorController {

    @Autowired
    VideoProcessorService videoProcessorService;

    @Value("${video.mime-types}")
    private List<String> contentVideos;

    @GetMapping("process/{fName}")
    public ResponseEntity upload(@PathVariable String fName) throws Exception {
        log.info(" Video processing intiated.");
        long sTime = System.currentTimeMillis();
        Response response = videoProcessorService.process(fName);
        log.info(" Video processing completed.");
        log.info(" Execution time: {}", System.currentTimeMillis() - sTime);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(value = "upload", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<?> processVideo(
            @RequestPart("content") MultipartFile file) throws Exception {
        long sTime = System.currentTimeMillis();
        String contentType = file.getContentType();
        log.info("ContentType: {}", contentType);
        if (!contentVideos.contains(contentType)) {
            log.info("video content Type {}", contentType);
            return new ResponseEntity<>("Unknown Video Format", HttpStatus.BAD_REQUEST);
        }
        String response = videoProcessorService.saveVideo(file);
        log.info(" Execution time: {}", System.currentTimeMillis() - sTime);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

