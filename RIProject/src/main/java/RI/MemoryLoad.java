package RI;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class MemoryLoad {

    public void saveIndex(HashMap<String, Tuple> invertedIndex) throws IOException {
        PrintWriter printWriter = new PrintWriter("index.txt");
        printWriter.println(invertedIndex);
        printWriter.close();
    }
}
