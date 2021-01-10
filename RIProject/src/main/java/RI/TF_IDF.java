package RI;

import java.util.ArrayList;
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
            TF = 1 + (Math.log(termFrequencies.get(term)) / Math.log(2.0));

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
            invertedIndex
                .get(term)
                .setIDF(Math.log(nFiles / invertedIndex.get(term).getWeightDoc().size()));
        }
    }

    public void calculateLong(HashMap<String, Tuple> invertedIndex, HashMap<String, Double> longDoc) {
        double weight;

        for (String term : invertedIndex.keySet()) {
            weight = 0.0;

            for (String doc : invertedIndex.get(term).getWeightDoc().keySet()) {
                weight =
                    Math.pow(invertedIndex.get(term).getWeightDoc().get(doc) * invertedIndex.get(term).getIDF(), 2);

                if (longDoc.containsKey(doc)) {
                    weight += longDoc.get(doc);
                }

                longDoc.put(doc, weight);
            }
        }

        for (String doc : longDoc.keySet()) {
            longDoc.put(doc, Math.sqrt(longDoc.get(doc)));
        }
    }

    public void calculateSearchTF_IDF(
        HashMap<String, Tuple> invertedIndex,
        HashMap<String, Double> longDoc,
        ArrayList<String> searchTerms,
        HashMap<String, Double> score
    ) {
        double IDF, weight, searchTF_IDF;

        for (String term : searchTerms) {
            if (invertedIndex.containsKey(term)) {
                IDF = invertedIndex.get(term).getIDF();

                for (String idDoc : invertedIndex.get(term).getWeightDoc().keySet()) {
                    weight = invertedIndex.get(term).getWeightDoc().get(idDoc);

                    if (score.containsKey(idDoc)) {
                        searchTF_IDF = score.get(idDoc) + (weight * Math.pow(IDF, 2));
                    } else {
                        searchTF_IDF = weight * Math.pow(IDF, 2);
                    }

                    score.put(idDoc, searchTF_IDF);
                }
            }
        }

        for (String idDoc : score.keySet()) {
            score.put(idDoc, score.get(idDoc) / longDoc.get(idDoc));
        }
    }
}
