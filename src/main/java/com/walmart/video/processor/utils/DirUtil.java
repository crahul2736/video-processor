package com.walmart.video.processor.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class DirUtil {


    public static void clearDir(String path) throws IOException {
        Path pathToBeDeleted = new File(path).toPath().toAbsolutePath();
        if (!(pathToBeDeleted.toFile().listFiles().length == 0)) {
            Files.walk(pathToBeDeleted)
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(File::delete);
            log.info("Directory cleared :{}", path);
        }
    }

    public static void createDir(String path) throws Exception {
        File pathAsFile = new File(path);
        if (!Files.exists(Paths.get(path))) {
            pathAsFile.mkdir();
        }
    }

    public static List<Path> getFilePaths(String baseDir) throws Exception {
        List<Path> pathList;
        try (Stream<Path> stream = Files.walk(Paths.get(baseDir))) {
            pathList = stream.map(Path::normalize)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }

        return pathList;
    }
}
