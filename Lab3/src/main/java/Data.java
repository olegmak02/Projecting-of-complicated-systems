import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Data {
    public static List<String> names1 = new ArrayList<>();
    public static List<String> names2 = new ArrayList<>();

     public static void init() {
         Scanner scanner1 = null;
         Scanner scanner2 = null;

         File file1 = new File(".\\data_1.txt");
         File file2 = new File(".\\data_2.txt");

         try {
             scanner1 = new Scanner(file1);
             scanner2 = new Scanner(file2);

             for (int i = 0; i < 7500; i++) {
                 names1.add(scanner1.nextLine());
             }

             for (int i = 7500; i < 15000; i++) {
                 names2.add(scanner2.nextLine());
             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }

        scanner1.close();

        scanner2.close();
    }
}
