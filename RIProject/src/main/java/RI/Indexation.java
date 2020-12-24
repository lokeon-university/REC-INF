package RI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Indexation {

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

    public Indexation() {}

    public void start() throws IOException {
        Filters filter = new Filters();
       
        System.out.println("\nApplaying filters...\n");
        //Filters
        for (File f : corpus.listFiles()) {
            text = new String(Files.readAllBytes(Paths.get(f.getPath())));

            //Filtering characters...
            textFiltered = filter.filterCharacters(text);

            //Filtering stopwords...
            terms = new ArrayList<String>(Arrays.asList(textFiltered.split(" ")));
            terms = filter.filterStopwords(terms);

            //Stemming...
            terms = stemming.stemming(terms);

            //Filtering by threshold...
            terms = filter.filterThreshold(terms);

            //Frecuencies
            termFrequencies = termFrecuency.frequencies(terms);

            //Inverted Index with TF
            tf_IDF.calculateTF(invertedIndex, termFrequencies, f.getName());
        }

        System.out.println("Calculating Inverted Index\n");
        //Inverted Index with TF-IDF
        tf_IDF.calculateIDF(invertedIndex, corpus.list().length);
        
        System.out.println("Saving in index.txt...\n");
        //Save Index
        memoryLoad.saveIndex(invertedIndex);
       
        System.out.println("Calculating Document Length\n"); 
        //Long Doc
        tf_IDF.calculateLong(invertedIndex, longDoc);

        System.out.println("Saving in long.txt...\n");
        //Save Long
        memoryLoad.saveLong(longDoc);
        
        System.out.println("Indexation Complete!\n");
        
    }
}
