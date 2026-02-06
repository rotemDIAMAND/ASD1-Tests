package test2;
import java.io.*;
import java.util.Random;

public class MainTrain1 {
    private static final String FILE_NAME = "test_enc.txt";
    private static final int TESTS_COUNT = 3;
    private static final int POINTS_PER_TEST = 11;
    private static final int TOTAL_POINTS = TESTS_COUNT * POINTS_PER_TEST;

    public static void main(String[] args) {
        int totalScore = TOTAL_POINTS;

        try {
            if (!testEncryption()) {
                System.out.println("Encryption test failed (-11)\n");
                totalScore -= 11;
            }
            if (!testDecryption()) {
                System.out.println("Decryption test failed (-11)\n");
                totalScore -= 11;
            }
            if (!testCombined()) {
                System.out.println("Combined test failed (-11)\n");
                totalScore -= 11;
            }
        } catch (Exception e) {
            System.out.println("An exception occurred during testing, all remaining points lost (-" + totalScore + ")\n");
            totalScore = 0;
        }

        System.out.println("done");
    }

    private static boolean testEncryption() throws IOException {
        String randomText = generateRandomString(10);

        // Encrypt and write to file
        try (Writer fileWriter = new FileWriter(FILE_NAME);
             EncryptedWriter encWriter = new EncryptedWriter(fileWriter)) {
            encWriter.write(randomText);
        }

        // Read raw content from file
        StringBuilder readEncrypted = new StringBuilder();
        try (Reader fileReader = new FileReader(FILE_NAME)) {
            int ch;
            while ((ch = fileReader.read()) != -1) {
                readEncrypted.append((char) ch);
            }
        }
        StringBuilder encrypted = new StringBuilder();
        for (char c : randomText.toCharArray()) {
            encrypted.append((char) (c + 3)); // Caesar cipher shift
        }
        String expectedEncrypted = encrypted.toString();
        return readEncrypted.toString().equals(expectedEncrypted);
    }

    private static boolean testDecryption() throws IOException {
        String randomText = generateRandomString(10);
        StringBuilder encrypted = new StringBuilder();
        for (char c : randomText.toCharArray()) {
            encrypted.append((char) (c + 3)); // Caesar cipher shift
        }
        String expectedEncrypted = encrypted.toString();

        // Write encrypted text directly to file
        try (Writer fileWriter = new FileWriter(FILE_NAME)) {
            fileWriter.write(expectedEncrypted);
        }

        // Read and decrypt
        StringBuilder decryptedText = new StringBuilder();
        try (Reader fileReader = new FileReader(FILE_NAME);
             EncryptedReader encReader = new EncryptedReader(fileReader)) {
            char[] buffer = new char[100];
            int len = encReader.read(buffer, 0, buffer.length);
            decryptedText.append(buffer, 0, len);
        }

        return decryptedText.toString().equals(randomText);
    }

    private static boolean testCombined() throws IOException {
        String randomText = generateRandomString(10);

        // Encrypt and write to file
        try (Writer fileWriter = new FileWriter(FILE_NAME);
             EncryptedWriter encWriter = new EncryptedWriter(fileWriter)) {
            encWriter.write(randomText);
        }

        // Read and decrypt
        StringBuilder decryptedText = new StringBuilder();
        try (Reader fileReader = new FileReader(FILE_NAME);
             EncryptedReader encReader = new EncryptedReader(fileReader)) {
            char[] buffer = new char[100];
            int len = encReader.read(buffer, 0, buffer.length);
            decryptedText.append(buffer, 0, len);
        }

        return decryptedText.toString().equals(randomText);
    }

    private static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ('A' + random.nextInt(26))); // Random uppercase letter
        }
        return sb.toString();
    }

}
