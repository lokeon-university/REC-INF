package RI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Search {

    private Stemming stemming = new Stemming();
    private TF_IDF tf_IDF = new TF_IDF();
    private MemoryLoad memoryLoad = new MemoryLoad();
    private HashMap<String, Tuple> invertedIndex = new HashMap<String, Tuple>();
    private HashMap<String, Double> longDoc = new HashMap<String, Double>();
    private HashMap<String, Double> score = new HashMap<String, Double>();
    private ArrayList<String> searchTerms;
    private String searchFiltered;

    public Search() {}

    public void orderbyValueDescent(HashMap<String, Double> score) {
        LinkedHashMap<String, Double> reverseSortedMap = new LinkedHashMap<>();
        score
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
    }

    public void start(String search) throws IOException {
        Filters filter = new Filters();

        if (invertedIndex.isEmpty()) {
            invertedIndex = memoryLoad.loadIndex();
        }

        if (longDoc.isEmpty()) {
            longDoc = memoryLoad.loadDoc();
        }

        //Filtering characters...
        searchFiltered = filter.filterCharacters(search);

        //Filtering stopwords...
        searchTerms = new ArrayList<String>(Arrays.asList(searchFiltered.split(" ")));
        searchTerms = filter.filterStopwords(searchTerms);

        //Stemming...
        searchTerms = stemming.stemming(searchTerms);

        //Filtering by threshold...
        searchTerms = filter.filterThreshold(searchTerms);

        //Calculate Score
        tf_IDF.calculateSearchTF_IDF(invertedIndex, longDoc, searchTerms, score);

        for (String name : score.keySet()) {
            String key = name.toString();
            String value = score.get(name).toString();
            System.out.println(key + " " + value);
        }
        // orderbyValueDescent(score);
    }
}
