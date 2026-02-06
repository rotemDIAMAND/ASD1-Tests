package test1;

import java.io.StringReader;
import java.io.StringWriter;


public class MainTrain1 {
    public static void testWriter(String testString){
        try {
            // Test LowerWriter
            StringWriter sw = new StringWriter();
            LowerWriter lw = new LowerWriter(sw);
            lw.write(testString.toCharArray(), 0, testString.length());
            lw.close();
            String result = sw.toString();
            if (!result.equals(testString.toLowerCase())) {
                System.out.println("LowerWriter failed (-4)");
            }        
        } catch (Exception e) {
            System.out.println("LowerWriter encountered exception (-4)");
        }    
    }

    public static void testReader(String testString, String expected){
        try{
            CapsReader cr=new CapsReader(new StringReader(testString));
            char[] buffer = new char[testString.length()];
            cr.read(buffer, 0, buffer.length);
            cr.close();
            String result = new String(buffer);
            if(!expected.equals(result))
                System.out.println("CapsReader failed (-4)");
        }catch(Exception e){
            System.out.println("CapsReader encountered exception (-4)");
        }
    }

    public static void main(String[] args) {
        testWriter("hello world");
        testWriter("Hello World");
        testWriter("HELLO WORLD");
        testWriter("HELLO worlD");

        testReader("hello world", "Hello World");
        testReader("Hello World", "Hello World");
        testReader("heLLo worlD", "Hello World");
        testReader("HELLO WORLD", "Hello World");

        System.out.println("done");        
    }
}
