import org.apache.tika.Tika;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.CloseShieldInputStream;
import org.apache.tika.io.IOUtils;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MainClass {

    public static void main(String[] args) throws IOException {
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


        final Detector detector = new DefaultDetector();
        final Metadata metadata = new Metadata();

        System.out.println("########################Using Apache Tika- FileInputStream#################################");
        for (File file1 : file.listFiles()) {

            FileInputStream fileInputStream = new FileInputStream(file1);
            TikaInputStream tikaInputStreamStream = TikaInputStream.get(new CloseShieldInputStream(fileInputStream));
            try {
                metadata.set(Metadata.RESOURCE_NAME_KEY, file1.getName());
                // System.out.println("FileName:" + file1.getName() + "---- Content Type:" + defaultTika.detect(fileInputStream));4
                System.out.println("FileName:" + file1.getName() + "---- Content Type:" + detector.detect(tikaInputStreamStream, metadata).toString());
            } catch (IOException e) {
                System.out.println("Failed to determine media type for '" + file1.getName());
            } finally {
                IOUtils.closeQuietly(tikaInputStreamStream);
                fileInputStream.close();
            }

        }


    }
}
