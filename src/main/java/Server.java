import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    private static long lifeCycleServerInMillis = 20_000;
    private static Status serverStatus = Status.WORKING;


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8008);
        int counter = 0;
        Random random = new Random();
        final long before = System.currentTimeMillis();
        while (System.currentTimeMillis() - before < lifeCycleServerInMillis) {
            Socket clientSocket = serverSocket.accept();
            OutputStreamWriter writer = Utils.getWriter(clientSocket);
            BufferedReader reader = Utils.getReader(clientSocket);
            String request = reader.readLine();
            String response;
            if (!request.startsWith("|INFO|")) {
                response = "User № " + ++counter + " : not a valid command";
            } else {
                response = "Your identification number is " + ++counter + ", and random num is " + (byte) random.nextInt(256) + "\n";
            }

            writer.write(response);
            writer.flush();

            reader.close();
            writer.close();
            clientSocket.close();
        }
        setServerStatus(Status.STOPPED);
        serverSocket.close();
    }

    public static boolean isServerWorking() {
        return serverStatus == Status.WORKING;
    }

    private static void setServerStatus(Status serverStatus) {
        Server.serverStatus = serverStatus;
    }


    private enum Status {
        WORKING, STOPPED
    }
}
