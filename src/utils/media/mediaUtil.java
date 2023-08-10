package utils.media;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import utils.generator.FileNameGenerator;

public class mediaUtil {
    private static final String imageBasePath = "resources/assets/documents/";
    public static String saveImage(String filePath) {
        String fileName = FileNameGenerator.generate();
        File savedImage = new File(imageBasePath + fileName + ".png");
        System.out.println(filePath);
        try {
            savedImage.getParentFile().mkdirs();
            savedImage.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(savedImage);
            outputStream.write(Files.readAllBytes(Path.of(filePath)));
            outputStream.close();
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}