import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Index {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        
        SMS sms = new SMS();
        
        sms.index();
    }
}
