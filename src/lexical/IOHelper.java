package lexical;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tjDu on 2016/10/20.
 */
public class IOHelper {
    public static ArrayList<String> readFileByLine(String path) {
        File file = new File(path);
        FileReader fileReader;
        ArrayList<String> dataList = null;
        BufferedReader br;
        try {
            fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            dataList = new ArrayList<>();
            String temp;
            while ((temp = br.readLine()) != null) {
                dataList.add(temp);
            }
            br.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public static void writeFile(List<String> list) {
        String path = "file/output.txt";
        File file = new File(path);
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);
            for (String str : list) {
                writer.write(str);
                writer.newLine();
            }
            writer.flush();
            writer.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Character> readFileByChar(String path) {
        ArrayList<String> temp = readFileByLine(path);
        List<Character> result = new ArrayList<>();
        for (String str : temp) {
            for (char c : str.toCharArray())
                result.add(c);
        }
        return result;
    }
}
