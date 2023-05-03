import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        while (Server.isServerWorking()) {
            Socket clientSocket;
            try {
                clientSocket = new Socket("127.0.0.1", 8008);
            } catch (Exception e) {
                break;
            }
            Thread.sleep(1000);
            BufferedReader reader = Utils.getReader(clientSocket);
            OutputStreamWriter writer = Utils.getWriter(clientSocket);
            writer.write("|INFsO| get client number and random symbol\n");
            writer.flush();
            System.out.println(reader.readLine());

            reader.close();
            writer.close();
            clientSocket.close();
        }
    }
}
