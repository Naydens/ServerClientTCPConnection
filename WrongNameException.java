package TZ;

public class WrongNameException extends Exception {
    private String message = "you inputted wrong name";
    private String wrongChars= " wrong chars in file name *  < > ? \\ | \" ";

    public String toString(){
        return message;
    }
    public String getWrongChars(){
        return wrongChars;
    }
}
