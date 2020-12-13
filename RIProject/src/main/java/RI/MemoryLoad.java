package RI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
}
