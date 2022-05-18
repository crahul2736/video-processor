package com.walmart.video.processor.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

@Slf4j
public class DirUtil {


    public static void clearDir(String path) throws IOException {
        Path pathToBeDeleted = new File(path).toPath().toAbsolutePath();
        if(!(pathToBeDeleted.toFile().listFiles().length == 0)){
            Files.walk(pathToBeDeleted)
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(File::delete);
            log.info("Directory cleared :{}", path);

        }

    }
}
