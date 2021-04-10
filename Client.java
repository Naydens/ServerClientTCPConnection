package TZ;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("127.0.0.1", 8085);
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        boolean exit = true;
        while (exit) {
            System.out.println("enter file's name and path separated \"/\". " +
                    "\n path MUST TO be separate / " +
                    "\n in the end enter ! \n File name mustn't have next symbols " +
                    "*  / < > ? \\ | \" \n" +
                    "For exit input 9");

            String info = scanner.nextLine();

            if (info.toCharArray()[0] == Constants.exitConst) {
                System.out.println("Good Bay");
                exit = false;

            } else {
                String[] arrInfo = info.split("/");
                String name = arrInfo[0];
                try {
                    if (HandlerClasses.checkFileNameSideClientRegex(name)) {
                        HandlerClasses.write(outputStream, info.getBytes());
                    }
                    String answerSearchFileOnServer = HandlerClasses.convertToStringBytes(inputStream);
                    if (answerSearchFileOnServer.equalsIgnoreCase(Constants.foundFileConst)) {
                        System.out.println("file was found, do you want to download file? want input - " + (char)Constants.downloadFileConst +
                                "otherwise - " + (char)Constants.notDownloadFileConst);

                        int answer = scanner.nextInt();
                        switch (answer) {
                            case Constants.downloadFileConst:
                                System.out.println("path to file " +
                                        "\n" + Constants.pathToFolderClient);
                                HandlerClasses.write(outputStream, info.getBytes());
                        }
                    }
                    else {
                        System.out.println("file wasn't find, do you want to continue? " +
                                "\n yes - press \"enter\" " +
                                "\n no - " +(char)Constants.exitConst);
                        int answer = scanner.nextInt();
                        switch (answer){
                            case Constants.
                        }
                        exit=false;
                    }

                } catch (DidnotInputFileNameException e) {
                    System.out.println(e.toString() + "\n try again");
                    continue;

                } catch (WrongNameException e) {
                    System.out.println(e.toString() + "\n" + e.getWrongChars() + "\n try again");
                    continue;
                } catch (IOException e) {
                    e.getMessage();
                    break;
                }
            }
        }
    }


//        socket.close();


    public static void showStructure(File folder) {

        String[] str = folder.list();
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
//        for (File elem : folder.listFiles()) {
////            System.out.println(elem);
//            if (elem.isDirectory()) {
//                showStructure(elem);
//            }
//        }
    }


}

