package test1;
import java.io.Writer;
import java.io.IOException;

public class LowerWriter extends Writer {
    // השדה ששומר את ה-Writer שאנחנו עוטפים (ה-Decorator pattern)
    private final Writer innerWriter;

    // קונסטרקטור שמקבל את היעד לכתיבה
    public LowerWriter(Writer innerWriter) {
        this.innerWriter = innerWriter;
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        // יוצרים עותק של המערך כדי לא לשנות את המערך המקורי של הקורא (Side effect)
        char[] lowerCbuf = new char[len];

        for (int i = 0; i < len; i++) {
            lowerCbuf[i] = Character.toLowerCase(cbuf[off + i]);
        }

        // כותבים את המערך המוקטן ל-Writer הפנימי
        innerWriter.write(lowerCbuf, 0, len);
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