package RI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Search {

    private Stemming stemming = new Stemming();
    private TF_IDF tf_IDF = new TF_IDF();
    private MemoryLoad memoryLoad = new MemoryLoad();
    private HashMap<String, Tuple> invertedIndex = new HashMap<String, Tuple>();
    private HashMap<String, Double> longDoc = new HashMap<String, Double>();
    private HashMap<String, Double> score = new HashMap<String, Double>();
    private HashMap<String, Double> scoreSorted = new HashMap<String, Double>();
    private ArrayList<String> searchTerms;
    private String searchFiltered;

    public Search() {}

    public HashMap<String, Double> orderbyValueDescent(HashMap<String, Double> score, int nResults) {
        return score
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(nResults)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public void start(String search, int nResults) throws IOException {
        Filters filter = new Filters();

        if (invertedIndex.isEmpty()) {
            invertedIndex = memoryLoad.loadIndex();
        }

        if (longDoc.isEmpty()) {
            longDoc = memoryLoad.loadDoc();
        }

        System.out.println(" Applaying character filter\n");
        //Filtering characters...
        searchFiltered = filter.filterCharacters(search);

        System.out.println(" Applaying stopwords filter\n");
        //Filtering stopwords...
        searchTerms = new ArrayList<String>(Arrays.asList(searchFiltered.split(" ")));
        searchTerms = filter.filterStopwords(searchTerms);

        System.out.println(" Applaying stemming\n");
        //Stemming...
        searchTerms = stemming.stemming(searchTerms);

        System.out.println(" Applaying threshold filter\n");
        //Filtering by threshold...
        searchTerms = filter.filterThreshold(searchTerms);

        //Calculate Score
        tf_IDF.calculateSearchTF_IDF(invertedIndex, longDoc, searchTerms, score);

        //Sorted Score by Value descending
        scoreSorted = orderbyValueDescent(score, nResults);

        System.out.println("RESULTS: \n");

        for (String doc : scoreSorted.keySet()) {
            String idDoc = doc.toString();
            String weight = scoreSorted.get(doc).toString();
            System.out.println("Document ID: " + idDoc + " Weight: " + weight);
        }
    }
}
