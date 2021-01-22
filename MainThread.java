package TZ;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainThread {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        while (true) {
            System.out.println("wait connection....");
            Socket socket = serverSocket.accept(); // на каждое принятое соединение создается поток
            MyRunnableClass myRunnableClass = new MyRunnableClass(socket);
            Thread ioThread = new Thread(myRunnableClass);//как только оно обработано поток убивается, постоянно выделяю ресурс у ОС и возвращаю
            ioThread.start();// PoolThread позволяет этого избежать

            System.out.println("main thread");
        }
    }


}
