package sort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dnermolaev
 */

public class Main {

    public static void main(String[] args) throws IOException, AttributesException, NumberFormatException {

        List <String> inputFileNames = new ArrayList<>();
        String outputFileName = null;
        ArrayList<String> disOrderList = new ArrayList<>();
        Boolean integerType;
        Boolean isAscending=null;
        List result=new ArrayList();

        ComLineParser comLineParser = new ComLineParser(args);
        Reader reader = new Reader();
        Sorter sorter=new Sorter(new MergeSortInt());
        Writer writer = new Writer();

        // проверка параметров командной строки
        comLineParser.checkAttributes();

        // определение имени выходного файла
        outputFileName=comLineParser.parseOutputName();

        // определение имени входного файла
        inputFileNames =comLineParser.parseInputName();

        //чтение исходных файлов
        reader.read(inputFileNames, disOrderList);

        //проверка и определение типа входных данных, порядка сортировки
        integerType=comLineParser.checkType(disOrderList);
        isAscending=comLineParser.order();

        //применение сортировки
        if (integerType == true){
            result = sorter.sort(disOrderList, isAscending);
        }

        if (integerType == false){
            sorter.setAlgorithm(new MergeSortString());
            result = sorter.sort(disOrderList, isAscending);
        }

        // запись в файл
        writer.write(outputFileName, result);
        System.out.println("Sorting is complete");
        }

    }
