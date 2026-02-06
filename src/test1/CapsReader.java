package test1;
import java.io.Reader;
import java.io.IOException;

public class CapsReader extends Reader {
    private final Reader innerReader;
    private boolean lastWasWhitespace = true; // מתחילים כ-true כדי שהאות הראשונה תהיה גדולה

    public CapsReader(Reader innerReader) {
        this.innerReader = innerReader;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int result = innerReader.read(cbuf, off, len);

        if (result != -1) {
            for (int i = off; i < off + result; i++) {
                char current = cbuf[i];

                if (Character.isWhitespace(current)) {
                    lastWasWhitespace = true;
                } else {
                    if (lastWasWhitespace) {
                        cbuf[i] = Character.toUpperCase(current);
                        lastWasWhitespace = false;
                    } else {
                        cbuf[i] = Character.toLowerCase(current);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        innerReader.close();
    }
}