package RI;

import java.util.HashMap;

public class TF_IDF {

    public void calculateTF(
        HashMap<String, Tuple> invertedIndex,
        HashMap<String, Integer> termFrequencies,
        String filename
    ) {
        HashMap<String, Double> weightDoc;

        double TF = 0.0;

        for (String term : termFrequencies.keySet()) {
            TF = 1 + Math.log(termFrequencies.get(term)) / Math.log(2.0);

            if (invertedIndex.containsKey(term)) {
                weightDoc = invertedIndex.get(term).getWeightDoc();
            } else {
                weightDoc = new HashMap<String, Double>();
            }
            weightDoc.put(filename, TF);
            invertedIndex.put(term, new Tuple(0.0, weightDoc));
        }
    }

    public void calculateIDF(HashMap<String, Tuple> invertedIndex, int nFiles) {
        for (String term : invertedIndex.keySet()) {
            invertedIndex.get(term).setIDF(Math.log((double) nFiles / invertedIndex.get(term).getWeightDoc().size()));
        }
    }
}
