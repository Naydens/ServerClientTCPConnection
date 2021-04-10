package TZ;

import java.io.*;
import java.net.Socket;


public class MyRunnableClass implements Runnable {
    private Socket socket;


    public MyRunnableClass(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        while (true) {
            try {
                outputStream = this.socket.getOutputStream();
                inputStream = this.socket.getInputStream();

                String info = HandlerClasses.convertToStringBytes(inputStream);
                String[] arrInfo = info.split("/");

                String name = arrInfo[0];
                String path = arrInfo[1];

                int checkFileName = HandlerClasses.checkFileNameSideServerRegex(name);
                switch (checkFileName) {
                    case Constants.okNameConst:
                        File root = new File(path+"\\");
                        String answer = HandlerClasses.fileSearch(name,root);
                        HandlerClasses.write(outputStream,answer.getBytes());
                        break;
                    case Constants.wrongNameConst:
                        HandlerClasses.write(outputStream, Constants.wrongNameConst);
                        break;
                    case Constants.notInputFileNameConst:
                        HandlerClasses.write(outputStream, Constants.notInputFileNameConst);
                        break;
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("1 catch");
                break;
            } finally {
                //will write for null
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    System.out.println("2 catch");
                    break;
                }
            }
        }
    }
}



