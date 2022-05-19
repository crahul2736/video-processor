package com.walmart.video.processor.service;

import com.walmart.video.processor.utils.DirUtil;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Service
@Slf4j
public class VideoToImageConverter {

    @Value("${img.scanned.path}")
    String imgScannedPath;

    @Value("${frame.count}")
    int frameCount;
    /**
     * Get frames/images from video
     * @return
     * @throws Exception
     */
    public String convertVideoToImage(String videoPath) throws Exception {
        log.info("Image generation started.");
        log.info("Video file path: {}", videoPath);
        DirUtil.clearDir(imgScannedPath);
        try (FFmpegFrameGrabber g = new FFmpegFrameGrabber(videoPath)) {
            g.start();
            for (int i = 0; i < frameCount; i++) {
                Frame frame = g.grabImage();
                BufferedImage image = new Java2DFrameConverter().convert(frame);
                ImageIO.write(image, "png", new File(imgScannedPath + "img-" +System.currentTimeMillis() + ".png"));
            }
            g.stop();
        }
        log.info("Image generation completed.");
        return imgScannedPath;
    }


}
