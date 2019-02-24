package sort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static sort.ComLineParser.exitByEnterPressed;
import static sort.ComLineParser.exitMessage;

/**
 * @author dnermolaev
 */

public class Writer {

    public void write (String outputFileName, List list){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))){
            for (int i=0; i<list.size(); i++) {
                bw.write(list.get(i) + "\n");
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while writing output data." + exitMessage);
            exitByEnterPressed();
        }
    }
}
