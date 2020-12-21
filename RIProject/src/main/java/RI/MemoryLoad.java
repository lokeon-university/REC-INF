package RI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MemoryLoad {

    public void saveIndex(HashMap<String, Tuple> invertedIndex) throws IOException {
        File file = new File("index.txt");
        BufferedWriter bf = new BufferedWriter(new FileWriter(file));

        for (Map.Entry<String, Tuple> entry : invertedIndex.entrySet()) {
            bf.write(entry.getKey() + ":" + entry.getValue());
            bf.newLine();
        }
        bf.close();
    }

    public void saveLong(HashMap<String, Double> longDoc) throws IOException {
        File file = new File("long.txt");
        BufferedWriter bf = new BufferedWriter(new FileWriter(file));

        for (Map.Entry<String, Double> entry : longDoc.entrySet()) {
            bf.write(entry.getKey() + ":" + entry.getValue());
            bf.newLine();
        }
        bf.close();
    }

    public HashMap<String, Tuple> loadIndex() throws IOException {
        File file = new File("index.txt");
        Scanner input = new Scanner(file);
        List<String> list = new ArrayList<String>();
        HashMap<String, Tuple> invertedIndex = new HashMap<String, Tuple>();
        HashMap<String, Double> TF;

        while (input.hasNextLine()) {
            list.add(input.nextLine());
        }

        input.close();

        for (String pairs : list) {
            String[] pair = pairs.split(":");
            String[] splitTuple = pair[1].split("-");
            String sTF = splitTuple[1].trim().replaceAll("^\\{|\\}$", " ");
            TF = new HashMap<String, Double>();

            if (sTF.contains(",")) {
                String[] splitTFs = sTF.split(",");
                for (int i = 0; i < splitTFs.length; i++) {
                    String[] pspltTFs = splitTFs[i].split("=");
                    TF.put(pspltTFs[0].trim(), Double.parseDouble(pspltTFs[1].trim()));
                }
                invertedIndex.put(pair[0].trim(), new Tuple(Double.parseDouble(splitTuple[0].trim()), TF));
            } else {
                String[] splitTF = sTF.split("=");
                TF.put(splitTF[0].trim(), Double.parseDouble(splitTF[1].trim()));
                invertedIndex.put(pair[0].trim(), new Tuple(Double.parseDouble(splitTuple[0].trim()), TF));
            }
        }

        return invertedIndex;
    }

    public HashMap<String, Double> loadDoc() throws IOException {
        File file = new File("long.txt");
        Scanner input = new Scanner(file);
        List<String> list = new ArrayList<String>();
        HashMap<String, Double> longDoc = new HashMap<String, Double>();

        while (input.hasNextLine()) {
            list.add(input.nextLine());
        }

        input.close();

        for (String pairs : list) {
            String[] pair = pairs.split(":");
            longDoc.put(pair[0].trim(), Double.parseDouble(pair[1].trim()));
        }

        return longDoc;
    }
}
