package TZ;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class MyRunnableClass implements Runnable {
    private String nameFife;
    private String path = "C:\\Users\\1\\Desktop\\";
    private File file;

    public MyRunnableClass(String nameFife) {
        this.nameFife = nameFife;
        file = new File(path + nameFife);
    }

    @Override
    public void run() {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];
            int neededByte;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
