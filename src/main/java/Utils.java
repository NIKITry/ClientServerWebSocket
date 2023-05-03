import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Utils {

    private Utils() {
    }

    public static BufferedReader getReader(Socket socket) {
        try {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("writer or reader produced null");
    }

    public static OutputStreamWriter getWriter(Socket socket) {
        try {
            return new OutputStreamWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("writer or reader produced null");
    }
}
