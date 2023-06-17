package utils.generator;

import java.util.Random;

public class PasswordGenerator {

    public static String generate(){
        int leftLimit = 48;
        int rightLimit = 57;
        int targetStringLength = 4;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

        return generatedString;
    }
}
