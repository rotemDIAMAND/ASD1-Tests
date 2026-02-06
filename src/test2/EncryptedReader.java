package test2;

import java.io.*;

public class EncryptedReader extends Reader {
    private final Reader innerReader;
    private final int shift = 3; // ההזזה שביקשת

    public EncryptedReader(Reader innerReader) {
        this.innerReader = innerReader;
    }


    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        // קוראים את הנתונים המוצפנים מה-innerReader
        int numCharsRead = innerReader.read(cbuf, off, len);

        if (numCharsRead == -1) return -1; // סוף הקובץ

        // מפענחים כל תו שנקרא
        for (int i = off; i < off + numCharsRead; i++) {
            cbuf[i] = decrypt(cbuf[i]);
        }

        return numCharsRead;
    }

    private char decrypt(char c) {
        if (Character.isLetter(c)) {
            char base = Character.isLowerCase(c) ? 'a' : 'A';
            // אנחנו מוסיפים 26 לפני המודולו כדי למנוע מספר שלילי (למשל אם נוריד 3 מ-'a')
            return (char) ((c - base - shift + 26) % 26 + base);
        }
        return c;
    }

    @Override
    public void close() throws IOException {
        innerReader.close();
    }
}
