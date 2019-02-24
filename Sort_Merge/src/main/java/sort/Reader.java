package sort;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static sort.ComLineParser.exitByEnterPressed;
import static sort.ComLineParser.exitMessage;

public class Reader {

    public List read(List<String> inputFileNames, ArrayList<String> list) {

        for (String str : inputFileNames) {
            try (BufferedReader reader = new BufferedReader(new FileReader(str))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    list.add(line);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Input file not found." + exitMessage);
                exitByEnterPressed();
            }
            catch (IOException e) {
                System.out.println("Error while reading input data." + exitMessage);
                exitByEnterPressed();
            }
        }
        return list;
    }
}