package com.walmart.video.processor;

import com.walmart.video.processor.utils.DirUtil;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        /*String imgPath = "src/main/resources/img/img-0.png";
        String croppedImgPath = "src/main/resources/img/cropped/crp-img-01.png";

        BufferedImage originalImg = ImageIO.read(
                new File(imgPath));

        BufferedImage SubImg
                = originalImg.getSubimage(583, 790, 364, 214);
        File outputfile
                = new File(croppedImgPath);

        // Writing image in new file created
        ImageIO.write(SubImg, "png", outputfile);*/

        List<Path> pathList = new ArrayList<>();

        pathList = DirUtil.getFilePaths("src/main/resources/img/cropped/");

        for (Path p : pathList) {
            System.out.println("ho ho " + Arrays.asList(p.getFileName().toString().split("-")).get(0));
//            Arrays.asList(p.getFileName().toString().split("-")).get(0);
        }

     /*   FFmpegFrameGrabber g = new FFmpegFrameGrabber("src/main/resources/video/VID20220518121021.mp4");

        g.start();
        for (int i = 0; i < 5; i++) {
            Frame frame = g.grabImage();
            BufferedImage image = new Java2DFrameConverter().convert(frame);
            ImageIO.write(image, "png", new File("src/main/resources/img/video-frame-" + i  + ".png"));

        }


        g.stop();*/
//        DirUtil.clearDir("src/main/resources/img");

       /* String directory = "src/main/resources/img";
        List<Path> pathList = new ArrayList<>();

        try (Stream<Path> stream = Files.walk(Paths.get(directory))) {
            pathList = stream.map(Path::normalize)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }

        pathList.forEach(System.out::println);*/
    }
}
