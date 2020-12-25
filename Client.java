package TZ;

import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1",8080);
        OutputStream outputStream = socket.getOutputStream();
        String nameFile = "fileOutputStream.txt";
        byte [] arrByte = nameFile.getBytes();
        outputStream.write(arrByte);
        outputStream.flush();
        socket.close();

    }
}
