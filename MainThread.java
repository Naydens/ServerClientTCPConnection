package TZ;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainThread {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            System.out.println("wait connection....");

            Socket socket = serverSocket.accept();
            String fileName = reader(socket.getInputStream());

            MyRunnableClass myRunnableClass = new MyRunnableClass(fileName);

            Thread ioThread = new Thread(myRunnableClass);
            ioThread.start();
            System.out.println("main thread");


        }
    }

    public static String reader(InputStream stream) throws IOException {
        int i;
        StringBuilder sb = new StringBuilder();
        while ((i = stream.read()) != -1) {
            sb.append((char) i);
        }

        return sb.toString();
    }
}
