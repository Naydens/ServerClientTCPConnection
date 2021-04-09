package TZ;

import java.io.*;

public class HandlerClasses {
    public static int checkFileNameSideServer(String name) {
        int answer = Constants.okNameConst;
        char[] arrChars = name.toCharArray();
        if (arrChars.length == 0) {
            answer = Constants.notInputNameConst;
        }
        for (int i = 0; i < arrChars.length; i++) {
            if (arrChars[i] == 42 || arrChars[i] == 48 || arrChars[i] == 60
                    || arrChars[i] == 62 || arrChars[i] == 63 || arrChars[i] == 92 || arrChars[i] == 124
                    || arrChars[i] == 171 || arrChars[i] == 187) {
                answer = Constants.wrongNameConst;
            }
        }
        return answer;

    }

    public static String convertToStringBytes(InputStream stream) throws IOException {
        int i;
        StringBuilder sb = new StringBuilder();

        while ((i = stream.read()) != Constants.convertToStringConst) {
             sb.append((char) i);
        }
        return sb.toString();
    }

    public static boolean answerAboutSearchFile(InputStream stream) throws IOException {
        int i;
        while ((i = stream.read()) != -1) {
            if (i == Constants.wrongNameConst) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    //list.listFiles() may produce NullPointerException
    //The warning said "Dereference of 'folder.listFiles()' may produce 'java.lang.NullPointerException'".
    // I wondered why because there is an explicit NPE-check in line 2.
    // Then I realized that this check is pointless. listFiles() will search the given directory for files at every call.
    // So by having two calls in the code above, the directory could be empty when the second call executes, even when it was not at the time the first call hits.
    // (Also, listFiles() will return null if the given path is not a directory.)
    public static String fileSearch(String name, File root) {
        File file = new File(root+"\\" + name);
        if (file.exists()) {
            return "File was found";
        }

        return "File wasn't found";
    }

    public static void write(OutputStream outputStream, byte[] data) throws IOException {
        outputStream.write(data);
        outputStream.flush();
    }



}
