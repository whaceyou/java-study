package com.ace.file;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class PathAndFile {

    public static void main(String[] args) throws IOException {
//        unzip(Paths.get("C:\\Users\\aceyo\\Desktop\\456"), Paths.get("C:\\Users\\aceyo\\Desktop\\123.zip"));
        unzip1(Paths.get("C:\\Users\\aceyo\\Desktop\\789"), Paths.get("C:\\Users\\aceyo\\Desktop\\123.zip"));
//        zip(Paths.get("C:\\Users\\aceyo\\Desktop\\789"), Paths.get("C:\\Users\\aceyo\\Desktop\\789.zip"));
        delDirectory(Paths.get("C:\\Users\\aceyo\\Desktop\\789"));

    }

    public static void delDirectory(Path targetDir) throws IOException {
        if (!Files.isDirectory(targetDir)) {
            return;
        }
        Files.walkFileTree(targetDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.deleteIfExists(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.deleteIfExists(dir);
                return FileVisitResult.CONTINUE;
            }
        });

    }

    private static void unzip1(Path targetDir, Path targetZipFile) throws IOException {
        try (FileSystem fs = FileSystems.newFileSystem(targetZipFile, ClassLoader.getSystemClassLoader())) {
            Path fsPath = fs.getPath("/");
            Files.walkFileTree(fsPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path relativePathInsideZip = fsPath.relativize(file);
                    Path target = targetDir.resolve(relativePathInsideZip.toString());
                    if (Files.notExists(target.getParent())) {
                        Files.createDirectories(target.getParent());
                    }
                    Files.copy(file, target);
                    return FileVisitResult.CONTINUE;
                }

            });
        }


    }

    private static void unzip(Path targetDir, Path targetZipFile) throws IOException {

        if (!Files.isRegularFile(targetZipFile) || Files.isDirectory(targetZipFile)) {
            throw new IllegalArgumentException("target zip file must be a file");
        }
        if (!Files.isDirectory(targetDir)) {
            Files.createDirectories(targetDir);
        }

        try (ZipFile zipFile = new ZipFile(targetZipFile.toFile())) {
            zipFile.stream().forEachOrdered(zipEntry -> {
                Path absolute = targetDir.resolve(Paths.get(zipEntry.getName()));
                try {
                    if (zipEntry.isDirectory()) {
                        Files.createDirectories(absolute);
                    } else {

                        InputStream inputStream = zipFile.getInputStream(zipEntry);
                        if (Files.notExists(absolute.getParent())) {
                            Files.createDirectories(absolute.getParent());
                        }
                        Files.copy(inputStream, absolute);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private static void zip(Path targetDirOrFile, Path targetZipFile) throws IOException {

        Path parent = targetZipFile.getParent();
        if (!Files.exists(parent)) {
            Files.createDirectories(parent);
        }
        if (Files.notExists(targetDirOrFile)) {
            return;
        }
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(targetZipFile))) {
            if (!Files.isDirectory(targetDirOrFile)) {
                zipOutputStream.putNextEntry(new ZipEntry(targetDirOrFile.getFileName().toString()));
                Files.copy(targetDirOrFile, zipOutputStream);
                return;
            }
            Files.walkFileTree(targetDirOrFile, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("visitFile: " + file);
                    String string = targetDirOrFile.relativize(file).toString();
                    zipOutputStream.putNextEntry(new ZipEntry(string));
                    Files.copy(file, zipOutputStream);
                    return FileVisitResult.CONTINUE;
                }
            });

        }
    }

}