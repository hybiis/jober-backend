package com.javajober.core.config;

import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class FileDirectoryConfig {

    public String getDirectoryPath() {

        String separator = File.separator;
        LocalDate today = LocalDate.now();

        String directoryName = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String directoryPath = System.getProperty("user.home") + separator + "JavaJober" + separator +
                directoryName + separator;

        createDirectory(directoryPath);
        return directoryPath;
    }

    public void createDirectory(final String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}