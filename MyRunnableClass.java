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
        while ((i = stream.read()) != 33) {
            sb.append((char) i);
        }
        return sb.toString();
    }

    private void getAnswer(InputStream inStream, OutputStream outStream, File path) throws IOException {
        int i;
        while ((i = inStream.read()) != 121) {

            inStream = new FileInputStream("C:\\Users\\1\\Desktop\\file'sTree\\" + path);
            byte[] buffer = new byte[1024];
            int neededByte;
            while ((neededByte = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, neededByte);
            }
        }
    }

        private void answerFileSerch (OutputStream outputStream, String error) throws IOException {
            byte[] errorMessageFileNotFoundBytes = error.getBytes();
            outputStream.write(errorMessageFileNotFoundBytes);
            outputStream.flush();
        }

        private String fileSearch (String name, File root){
            //list.listFiles() may produce NullPointerException
//The warning said "Dereference of 'folder.listFiles()' may produce 'java.lang.NullPointerException'".
// I wondered why because there is an explicit NPE-check in line 2.
// Then I realized that this check is pointless. listFiles() will search the given directory for files at every call.
// So by having two calls in the code above, the directory could be empty when the second call executes, even when it was not at the time the first call hits.
// (Also, listFiles() will return null if the given path is not a directory.)
            File[] list = root.listFiles();
            String errorMesag = "";
            for (File item : list) {

                if (item.isFile()) {
                    if ((item.getName().equalsIgnoreCase(name))) {
                        errorMesag = "File was find!";
                    } else {

                        errorMesag = "File was'n find!";
                    }
                }
            }

            return errorMesag;

        }

        @Override
        public void run () {

            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                outputStream = this.socket.getOutputStream();
                inputStream = this.socket.getInputStream();

                String info = convertToStringBytes(inputStream);
                String[] arrInfo = info.split(",");
                String name = arrInfo[0];
                String path = arrInfo[1];

                //search file in directory


                File root = new File("C:\\Users\\1\\Desktop\\file'sTree\\" + path);

                answerFileSerch(outputStream, fileSearch(name, root));
                File downloadRoot = new File("C:\\Users\\1\\Desktop\\file'sTree\\" + path + "\\" + name);
                getAnswer(inputStream, outputStream, downloadRoot);
//
//            inputStream = new FileInputStream("C:\\Users\\1\\Desktop\\file'sTree\\" + path +"\\"+ name);
//            byte[] buffer = new byte[1024];
//            int neededByte;
//            while ((neededByte = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, neededByte);
//            }
//            inputStream.close();
//            outputStream.close();


            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                //will write for null
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }


            }
        }
    }



