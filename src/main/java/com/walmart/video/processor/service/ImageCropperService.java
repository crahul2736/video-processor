package com.walmart.video.processor.service;

import com.walmart.video.processor.utils.DirUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Service
@Slf4j
public class ImageCropperService {

    /**
     * Crop image
     * @param imgscannedPath
     * @param croppedImgPath
     * @param object
     * @param x
     * @param y
     * @param w
     * @param h
     * @throws Exception
     */
    public void cropImages(String imgscannedPath,String croppedImgPath, String object, int x, int y, int w, int h) throws Exception {
        log.info("Images details : {} {}  {} {} {} {}", imgscannedPath, object, x, y, w, h);
//        DirUtil.createDir(croppedImgPath);

        BufferedImage originalImg = ImageIO.read(
                new File(imgscannedPath));

        BufferedImage SubImg
                = originalImg.getSubimage(x, y, w, h);
        File outputfile
                = new File(croppedImgPath + object + "-" + System.currentTimeMillis() + ".png");

        // Writing image in new file created
        ImageIO.write(SubImg, "png", outputfile);
    }
}
