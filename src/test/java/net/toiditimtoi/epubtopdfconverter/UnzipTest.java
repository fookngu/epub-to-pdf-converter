package net.toiditimtoi.epubtopdfconverter;

import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipTest {

    @Test
    public void testUnzipFile() {
        String zipFilePath = "/home/phucosg/workspace/epub-to-pdf-converter/src/test/resources/git-ignore-this/core-java2.epub"; // Specify the path to your zip file
        String destDirectory = "/home/phucosg/workspace/epub-to-pdf-converter/src/test/resources/git-ignore-this/output"; // Specify the destination folder

        try {
            unzip(zipFilePath, destDirectory);
            System.out.println("Unzip operation completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipInputStream.getNextEntry();
            while (entry != null) {
                String entryName = entry.getName();
                File entryFile = new File(destDirectory + File.separator + entryName);

                if (!entry.isDirectory()) {
                    // Create parent directories if needed
                    new File(entryFile.getParent()).mkdirs();

                    try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(entryFile))) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }
                } else {
                    entryFile.mkdir();
                }

                zipInputStream.closeEntry();
                entry = zipInputStream.getNextEntry();
            }
        }
    }
}
