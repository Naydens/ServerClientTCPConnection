package TZ;

import java.io.*;
import java.net.Socket;


   public class MyRunnableClass implements Runnable {
    private Socket socket;


    public MyRunnableClass(Socket socket) {
        this.socket = socket;
    }


    private void getAnswer(InputStream inStream, OutputStream outStream, File path) throws IOException {
        int i;
        if ((i = inStream.read()) == Constants.getAnswerConst) {

            inStream = new FileInputStream(path);
            byte[] buffer = new byte[Constants.bufferSizeConst];
            int neededByte;
            while ((neededByte = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, neededByte);
            }
        }
    }

    @Override
    public void run() {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        while (true) {
            try {
//                String str="hfskdhdufghid";
                outputStream = this.socket.getOutputStream();
                inputStream = this.socket.getInputStream();
//                HandlerClasses.write(outputStream,str.getBytes());
//                int i;
//                while ((i = inputStream.read()) != -1){
//                    System.out.println(i);
//                }

                System.out.println("run");
                String info = HandlerClasses.convertToStringBytes(inputStream);
                String[] arrInfo = info.split("/");
                System.out.println(arrInfo);
                String name = arrInfo[0];
                String path = arrInfo[1];
                System.out.println(name);
                if (HandlerClasses.checkFileNameSideServer(name)==Constants.okNameConst) {
                    System.out.println("ok name");
                    File root = new File(Constants.mainRootConst + path);
                    String answerFileSearch = HandlerClasses.fileSearch(name, root);
                    HandlerClasses.write(outputStream, answerFileSearch.getBytes());
                }
                else {
                    outputStream.write(HandlerClasses.checkFileNameSideServer(name));
                    outputStream.flush();
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



