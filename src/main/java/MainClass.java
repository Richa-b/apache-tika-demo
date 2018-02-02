import org.apache.tika.Tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MainClass {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World");

        System.out.println("Hello World");
        //Add path to your directory
        Path path = Paths.get("/home/richa/Desktop/TestFileType");
        Stream<Path> files = Files.walk(path);

        System.out.println("########################Using Files Api#################################");
        files.forEach(path1 -> {
            try {
                System.out.println("FileName:" + path1.getFileName() + "---- Content Type:" + Files.probeContentType(path1));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Tika defaultTika = new Tika();

        System.out.println("########################Using Apache Tika- File#################################");
        File file = new File("/home/richa/Desktop/TestFileType");
        for (File file1 : file.listFiles()) {
            System.out.println("FileName:" + file1.getName() + "---- Content Type:" + defaultTika.detect(file1));
        }

       /* file = new File("/home/richa/Desktop/SM_Docs/.ms_client_keystore");
        System.out.println("FileName:" + file.getName() + "---- Content Type:" + defaultTika.detect(file));*/

        System.out.println("########################Using Apache Tika- FileInputStream#################################");
        for (File file1 : file.listFiles()) {
            FileInputStream fileInputStream = new FileInputStream(file1);
            System.out.println("FileName:" + file1.getName() + "---- Content Type:" + defaultTika.detect(fileInputStream));
        }


    }
}
