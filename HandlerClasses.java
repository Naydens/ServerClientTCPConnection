package TZ;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandlerClasses {

//    ServerSide start

//    public static int checkFileNameSideServer(String name) {
//        int answer = Constants.okNameConst;
//        char[] arrChars = name.toCharArray();
//        if (arrChars.length == 0) {
//            answer = Constants.notInputFileNameConst;
//        }
//        for (int i = 0; i < arrChars.length; i++) {
//            if (arrChars[i] == 42 || arrChars[i] == 48 || arrChars[i] == 60
//                    || arrChars[i] == 62 || arrChars[i] == 63 || arrChars[i] == 92 || arrChars[i] == 124
//                    || arrChars[i] == 171 || arrChars[i] == 187) {
//                answer = Constants.wrongNameConst;
//            }
//        }
//        return answer;
//
//    }

    public static int checkFileNameSideServerRegex(String name) {
        int answerCheckFileNameServerRegex;
        if (name.length() == 0) {
          return Constants.notInputFileNameConst;
        }
        String regex = Constants.patternCheckFileNameConst;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);

        if (matcher.find()){
            answerCheckFileNameServerRegex = Constants.wrongNameConst;
        }
        else {
            answerCheckFileNameServerRegex = Constants.okNameConst;
        }

      return answerCheckFileNameServerRegex;
    }

    public static String convertToStringBytes(InputStream stream) throws IOException {
        int i;
        StringBuilder sb = new StringBuilder();

        while ((i = stream.read()) != Constants.convertToStringConst) {
            sb.append((char) i);
        }
        return sb.toString();
    }

    //list.listFiles() may produce NullPointerException
    //The warning said "Dereference of 'folder.listFiles()' may produce 'java.lang.NullPointerException'".
    // I wondered why because there is an explicit NPE-check in line 2.
    // Then I realized that this check is pointless. listFiles() will search the given directory for files at every call.
    // So by having two calls in the code above, the directory could be empty when the second call executes, even when it was not at the time the first call hits.
    // (Also, listFiles() will return null if the given path is not a directory.)
    public static String fileSearch(String name, File root) {
        File file = new File(root + name);
        if (file.exists()) {
            return Constants.foundFileConst ;
        }

        return Constants.notFoundFileConst;
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

//    ServerSide end


//CommonSide start

    public static void write(OutputStream outputStream, byte[] data) throws IOException {
        outputStream.write(data);
        outputStream.flush();
    }

    public static void write(OutputStream outputStream, int data) throws IOException {
        outputStream.write(data);
        outputStream.flush();
    }

    //CommonSide start


//ClientSide start

    public static boolean checkFileNameSideClientRegex(String name) throws DidnotInputFileNameException, WrongNameException {
        if (name.length() == 0) {
            throw new DidnotInputFileNameException();
        }

        String regex = Constants.patternCheckFileNameConst;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);

        if (matcher.find()) {
            throw new WrongNameException();
        } else {
            return true;
        }
    }

    public static void checkFileNameSideClient(String name) throws DidnotInputFileNameException, WrongNameException {
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

    //ClientSide end


}
