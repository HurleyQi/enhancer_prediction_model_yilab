import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataSplit {

    public static double TRAIN = 0.92;
    public static double TEST = 1 - TRAIN;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("parsed_tewhey_data_HKS.txt"));
        int file_line_number = 0;
        while (sc.hasNextLine()) {
            file_line_number++;
            sc.nextLine();
        }
        System.out.println("file line number: " + file_line_number);
        int train_line_number = (int)(file_line_number * TRAIN);
        // int test_line_number = (int)(file_line_number * TEST);

        FileWriter train_data = new FileWriter("tewhey_data_training_set");
        FileWriter test_data = new FileWriter("tewhey_data_testing_set");

        sc = new Scanner(new File("parsed_tewhey_data_HKS.txt"));

        int line_counter = 0;
        while (sc.hasNextLine()) {
            String curr = sc.nextLine() + "\n";
            if (line_counter < train_line_number) {
                train_data.write(curr);
            } else {
                test_data.write(curr);
            }
            line_counter++;
        }
        train_data.close();
        test_data.close();
        sc.close();
    }
}
