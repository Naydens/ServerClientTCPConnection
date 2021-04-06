package TZ;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        File download = new File("C:\\Users\\1\\Desktop\\clientDownload");
        File file = new File("C:\\Users\\1\\Desktop\\file'sTree");

        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("127.0.0.1", 8085);
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();

        System.out.println("enter file's name and path separated \"/\". " +
                "\n path MUST TO be separate / " +
                "\n in the end enter ! \n File name mustn't have next symbols " +
                "*  / < > ? \\ | \" \n" +
                "For enter input 0");

        String info = scanner.nextLine();

        if (info.toCharArray()[0] == Constants.notInputNameConst) {
            System.out.println("exit");
        } else {
            String[] arrInfo = info.split("/");
            String name = arrInfo[0];
            try {
                checkFileNameSideClient(name);
                HandlerClasses.write(outputStream, info.getBytes());
                System.out.println("start to run");
//                    int i;
//                    while ((i = inputStream.read()) != -1){
//                        System.out.println(i);
//                    }

//                    int checkFileNameServerSide;
//                    while ((checkFileNameServerSide = inputStream.read()) != -1) {
//
//                        if (checkFileNameServerSide == Constants.notInputNameConst) {
//                            throw new DidnotInputFileNameException();
//                        }
//                        if (checkFileNameServerSide == Constants.wrongNameConst) {
//                            throw new WrongNameException();
//                        } else {
//                            System.out.println("download file, file will download here \n " +
//                                    "C:\\Users\\1\\Desktop\\file'sTree ? \n" +
//                                    "yes input 1, no input 0");
//                            int answer = scanner.nextInt();
//                            switch (answer) {
//                                case 0:
//                                    break;
//                                case 1:
//
//                            }
//                        }
//                    }


            } catch (DidnotInputFileNameException e) {
                System.out.println(e.toString());

            } catch (WrongNameException e) {
                System.out.println(e.toString());
                System.out.println(e.getWrongChars());

            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

//            showStructure(new File("C:\\Users\\1\\Desktop\\file'sTree"));
//        socket.close();


    private static void checkFileNameSideClient(String name) throws DidnotInputFileNameException, WrongNameException {
        char[] arrChars = name.toCharArray();
        if (arrChars.length == 0) {
            throw new DidnotInputFileNameException();
        }
        for (int i = 0; i < arrChars.length; i++) {
            if (arrChars[i] == 42 || arrChars[i] == 48 || arrChars[i] == 60
                    || arrChars[i] == 62 || arrChars[i] == 63 || arrChars[i] == 92 || arrChars[i] == 124
                    || arrChars[i] == 171 || arrChars[i] == 187) {
                throw new WrongNameException();
            }
        }

    }


    public static void showStructure(File folder) {
        for (File elem : folder.listFiles()) {
            System.out.println(elem);
            if (elem.isDirectory()) {
                showStructure(elem);
            }
        }
    }


}

