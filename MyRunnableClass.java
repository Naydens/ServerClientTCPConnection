package TZ;

import java.io.*;
import java.net.Socket;


public class MyRunnableClass implements Runnable {
    private Socket socket;

    public MyRunnableClass(Socket socket) {
        this.socket = socket;
    }

    private String convertToStringBytes(InputStream stream) throws IOException {
        int i;
        StringBuilder sb = new StringBuilder();
        while ((i = stream.read()) != 10) {
            sb.append((char) i);
        }
        return sb.toString();
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String name = convertToStringBytes(this.socket.getInputStream());
            inputStream = new FileInputStream("C:\\Users\\1\\Desktop\\" + name);
            outputStream = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int neededByte;
            while ((neededByte = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, neededByte);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            //will write for null
            try {
                inputStream.close();
                outputStream.close();
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
        }

    }

}


