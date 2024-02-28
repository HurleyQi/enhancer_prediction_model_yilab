import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TewheyDataParse {

    public static String[] DNA_SEQUENCE = {"A","T","C","G"};

    public static int LIMIT = 163500;
    public static void main(String[] args) throws IOException {

        FileWriter fw = new FileWriter("parsed_tewhey_data_HKS.txt");

        // FileWriter train_set = new FileWriter("tewhey_testing_data");
        // FileWriter test_set = new FileWriter("tewhey_training_data");

        Scanner file_sc = new Scanner(new File("./tewhey_data_table_two.txt"));
        file_sc.nextLine();

        String HepG2_mean = new String();
        String K562_mean = new String();
        String SKNSH_mean = new String();
        String sequence = new String();
        int check = 0;
        int line_check = 0;
        while (file_sc.hasNextLine()) {
            String curr_line = file_sc.nextLine();
            Scanner line_sc = new Scanner(curr_line);
            HepG2_mean = line_sc.next();
            line_sc.next();
            K562_mean = line_sc.next();
            if (line_check < 783979) {
                line_sc.next();
            }
            SKNSH_mean = line_sc.next();
            String sequence_finder = new String();
            while (line_sc.hasNext()) {
                sequence_finder = line_sc.next();
                if (checkSequence(sequence_finder)) {
                    break;
                }
            }
            sequence = sequence_finder;
            String result = sequence + "," + HepG2_mean + "," + K562_mean + "," + SKNSH_mean + "\n";
            fw.write(result);

            // if (check < LIMIT) {
            //     train_set.write(result);
            // } else {
            //     test_set.write(result);
            // }
            // check++;
            // fw.write(result);
            line_check++;
            line_sc.close();
        }
        // train_set.close();
        // test_set.close();

        file_sc.close();
        System.out.println("line check: " + line_check);
    }

    public static boolean checkSequence(String sequence_finder) {
        if (sequence_finder.length() < 15) {
            return false;
        }
        for (int i = 0; i < sequence_finder.length(); i++) {
            if (! Arrays.asList(DNA_SEQUENCE).contains(sequence_finder.charAt(i) + "")) {
                return false;
            }
        }
        return true;
    }
}