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
        TermFrequency termFrecuency = new TermFrequency();
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
            HashMap<String, Integer> termFrequencies = termFrecuency.frecuencies(terms);

        }
    }

    public static void main(String[] args) throws IOException {
        indexation();
    }
}
