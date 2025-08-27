package com.example.auth_project.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
public class FileDownloadHelper {
    public Resource getFileAsResource(String uploadPath) throws IOException {
        Path uploadDirectory = Paths.get(uploadPath);
        Path parentDirectory = uploadDirectory.getParent();
        String fileName = uploadDirectory.getFileName().toString();

        try (Stream<Path> stream = Files.list(parentDirectory)) {
            return stream
                    .filter(file -> file.getFileName().toString().startsWith(fileName))
                    .findFirst()
                    .map(file -> {
                        try {
                            return new UrlResource(file.toUri());
                        } catch (IOException e) {
                            log.error("Error creating UrlResource for file: " + file, e);
                            return null;
                        }
                    })
                    .orElse(null);
        } catch (IOException e) {
            log.error("Error listing files in directory: " + parentDirectory, e);
            throw e;
        }catch (Exception e) {
            String errorMessage = "Error Directory Not Found ";
            log.error(errorMessage,e);
            throw new RuntimeException("Reject By System" + errorMessage);
        }
    }
}
