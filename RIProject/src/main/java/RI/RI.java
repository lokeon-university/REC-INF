package RI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RI {

    private static File corpus = new File("corpus1");

    public static void indexation() throws IOException {
        Filters filter = new Filters();
        Stemming stemming = new Stemming();
        TF_IDF tf_IDF = new TF_IDF();
        MemoryLoad memoryLoad = new MemoryLoad();
        TermFrequency termFrecuency = new TermFrequency();
        HashMap<String, Integer> termFrequencies;
        HashMap<String, Tuple> invertedIndex = new HashMap<String, Tuple>();
        HashMap<String,Double> longDoc = new HashMap<String, Double>();
        ArrayList<String> terms;
        String text = "", textFiltered;

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

            //Filtereing by threshold...
            terms = filter.filterThreshold(terms);

            // for (int i = 0; i < terms.size(); i++) {
            //     System.out.println(terms.get(i));
            // }

            //Frecuencies
            termFrequencies = termFrecuency.frequencies(terms);

            //Inverted Index with TF
            tf_IDF.calculateTF(invertedIndex, termFrequencies, f.getName());
        }

        //Inverted Index with TF-IDF
        tf_IDF.calculateIDF(invertedIndex, corpus.list().length);
        //Save Index
        memoryLoad.saveIndex(invertedIndex);
        //Long Doc
        tf_IDF.calculateLong(invertedIndex, longDoc);
        
    }

    public static void main(String[] args) throws IOException {
        indexation();
    }
}
