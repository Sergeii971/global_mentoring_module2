package com.os.module2.task3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileService {
    public long calculateFileCount(String path) {
         try (Stream<Path> pathStream = Files.walk(Paths.get(path))) {
             return pathStream.parallel()
                     .filter(Files::isRegularFile)
                     .count();
         } catch (IOException e) {
             throw new RuntimeException();
         }
    }

    public long calculateFolderCount(String path) {
        try (Stream<Path> pathStream = Files.walk(Paths.get(path))) {
            return pathStream.parallel()
                .filter(Files::isDirectory)
                .count();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public long calculateAllFilesSize(String path) {
        try (Stream<Path> pathStream = Files.walk(Paths.get(path))) {
            return pathStream.parallel()
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .mapToLong(File::length)
                .sum();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
