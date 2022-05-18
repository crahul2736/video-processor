package com.walmart.video.processor;

import com.walmart.video.processor.utils.DirUtil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
     /*   FFmpegFrameGrabber g = new FFmpegFrameGrabber("src/main/resources/video/VID20220518121021.mp4");

        g.start();
        for (int i = 0; i < 5; i++) {
            Frame frame = g.grabImage();
            BufferedImage image = new Java2DFrameConverter().convert(frame);
            ImageIO.write(image, "png", new File("src/main/resources/img/video-frame-" + i  + ".png"));

        }


        g.stop();*/
//        DirUtil.clearDir("src/main/resources/img");

        String directory = "src/main/resources/img";
        List<Path> pathList = new ArrayList<>();

        try (Stream<Path> stream = Files.walk(Paths.get(directory))) {
            pathList = stream.map(Path::normalize)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }

        pathList.forEach(System.out::println);
    }
}
