package sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//класс, отвечаюший за разбор параметров командной строки
public class ComLineParser {

    public static final String exitMessage = " Press Enter to exit";

    String[] keys;

    public ComLineParser(String[] keys) {
        this.keys = keys;
    }

    //определение имени выходного файла
    public String parseOutputName() {

        List<String> fileNames = new ArrayList<>();

        for (String str : keys) {
            if (str.contains(".txt")) {
                fileNames.add(str);
            }
        }
        return fileNames.get(0);
    }

    //определение имени входного файла(ов)
    public List parseInputName() {

        List<String> fileNames = new ArrayList<>();

        for (String str : keys) {
            if (str.contains(".txt")) {
                fileNames.add(str);
            }
        }
        fileNames.remove(0);
        return fileNames;
    }

    //проверка полноты и корректности заполнения атрибутов
    public void checkAttributes() {
        try {
            if (!(keys[0].equals("-i") || keys[0].equals("-s") || keys[1].equals("-i") || keys[1].equals("-s"))) {
                throw new AttributesException("Attributes in command line are empty or incorrect ");
            }
        } catch (ArrayIndexOutOfBoundsException | AttributesException e) {
            System.out.println("Attributes in command line are empty or incorrect. " + exitMessage);
            exitByEnterPressed();
        }
    }

    //определение типа данных
    public Boolean checkType(ArrayList<String> list) {
        Boolean type = null;
        List<String> keyList = new ArrayList<>();
        keyList = List.of(keys);
        if (keyList.contains("-i")) {
            int[] array = new int[list.size()];
            try {
                for (int i = 0; i < array.length; i++) {
                    array[i] = Integer.parseInt(String.valueOf(list.get(i)));
                }
                type = true;
            } catch (NumberFormatException e) {
                System.out.println("You are trying to sort string array as integer. " +
                        "The stated type of input data will be changed to string.");
                type = false;
            }
        }
        if (keyList.contains("-s")) {
            int[] array = new int[list.size()];
            try {
                for (int i = 0; i < array.length; i++) {
                    array[i] = Integer.parseInt(String.valueOf(list.get(i)));
                }
                System.out.println("You are trying to sort integer array as string. " +
                        "The stated type of input data will be changed to integer.");
                type = true;
            } catch (NumberFormatException e) {
                type = false;
            }
        }
        return type;
    }

    //определение порядка сортировки
    public Boolean order() {
        List<String> keyList = new ArrayList<>();
        keyList = List.of(keys);
        if (keyList.contains("-a"))
            return true;
        if (keyList.contains("-d"))
            return false;
        System.out.println("Incorrect type of order. Elements will be ordered ascending by default.");
        return true;
    }

    // завершение программы по нажатии пользователем Enter
    public static void exitByEnterPressed() {
        try {
            System.in.read();
            System.exit(1);
        } catch (IOException ignored) {

        }
    }
}
