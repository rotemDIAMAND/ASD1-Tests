package test2;

import java.io.*;
import java.io.IOException;
import java.io.Writer;

public class EncryptedWriter extends Writer {
    private final Writer innerWriter;
    private final int shift = 3;

    public EncryptedWriter(Writer innerWriter) {
        this.innerWriter = innerWriter;
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        char[] encryptedCbuf = new char[len];
        for (int i = 0; i < len; i++) {
            // הזזה פשוטה כפי שהטסט מצפה (c + 3)
            encryptedCbuf[i] = (char) (cbuf[off + i] + shift);
        }
        innerWriter.write(encryptedCbuf, 0, len);
    }

    @Override
    public void write(String str) throws IOException {
        write(str.toCharArray(), 0, str.length());
    }

    @Override
    public void flush() throws IOException {
        innerWriter.flush();
    }

    @Override
    public void close() throws IOException {
        innerWriter.close();
    }
}
