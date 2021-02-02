package TZ;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter file's name and path separated \",\". " +
                "\n path MUST TO be separate \\*2 " +
                "\n in the end enter !");
        String info = scanner.nextLine();
        Socket socket = new Socket("127.0.0.1", 8081);
        OutputStream outputStream = socket.getOutputStream();

        byte[] arrBytes = info.getBytes();
        //transmit bytes (file's name) on a TCP connection
        outputStream.write(arrBytes);
        //ask Igor about flush
        outputStream.flush();

        InputStream inputStream = socket.getInputStream();
        String answerFind = convertToStringBytes(inputStream);
        System.out.println(answerFind);

        System.out.println("\n do you want to download file?\n " +
                "yes ; no ?");
        String answer = scanner.nextLine();

        switch (answer) {
            case "yes":
                byte[] arrAnswer = answer.getBytes();
                outputStream.write(arrAnswer);
                outputStream.flush();
                byte[] buffer = new byte[1024];
                String s ="";

                int neededByte;
                while ((neededByte = inputStream.read(buffer)) != -1) {
                    System.out.println(s+new String(buffer));
                }

                break;
            case "no":
                break;
        }


        socket.close();

    }
    private static String convertToStringBytes(InputStream stream) throws IOException {
        int i;
        StringBuilder sb = new StringBuilder();
        while ((i = stream.read()) != 33) {
            sb.append((char) i);
        }
        return sb.toString();
    }


}

