package RI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Search {

    private static File corpus = new File("corpus1");
    private Stemming stemming = new Stemming();
    private TF_IDF tf_IDF = new TF_IDF();
    private MemoryLoad memoryLoad = new MemoryLoad();
    private TermFrequency termFrecuency = new TermFrequency();
    private HashMap<String, Integer> termFrequencies;
    private HashMap<String, Tuple> invertedIndex = new HashMap<String, Tuple>();
    private HashMap<String, Double> longDoc = new HashMap<String, Double>();
    private ArrayList<String> terms;
    private String text = "", textFiltered;

    public Search() {}

    public void start() throws IOException {
        invertedIndex = memoryLoad.loadIndex();
        longDoc = memoryLoad.loadDoc();
    }
}
