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

    private static String PATH_VIDEO = "src/main/resources/video/VID20220518121021.mp4";
    //    private static String PATH_IMG = "src/main/resources/img/";
    private static int IMG_COUNT = 5;
    @Value("${img.scanned.path}")
    String imgScannedPath;

    /**
     * Get frames/images from video
     * @return
     * @throws Exception
     */
    public String convertVideoToImage() throws Exception {
        log.info("Image generation started.");
        DirUtil.clearDir(imgScannedPath);
        try (FFmpegFrameGrabber g = new FFmpegFrameGrabber(PATH_VIDEO)) {
            g.start();
            for (int i = 0; i < IMG_COUNT; i++) {
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
