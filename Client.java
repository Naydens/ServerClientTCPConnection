package TZ;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8081);
        OutputStream outputStream = socket.getOutputStream();
        String nameFile = "fileOutputStream.txt\n";
        byte[] arrBytes = nameFile.getBytes();
        //transmit bytes (file's name) on a TCP connection
        outputStream.write(arrBytes);
        outputStream.flush();
        int i;
        InputStream inputStream = socket.getInputStream();
        while ((i = inputStream.read()) != -1) {
            System.out.print((char) i);
        }


        socket.close();

    }


}

