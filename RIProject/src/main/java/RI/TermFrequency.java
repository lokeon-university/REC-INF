package RI;

import java.util.ArrayList;
import java.util.HashMap;

public class TermFrequency {

    public HashMap<String, Integer> frecuencies(ArrayList<String> terms) {
        HashMap<String, Integer> termFrecuencies = new HashMap<String, Integer>();

        int frecuency = 0;
        for (int i = 0; i < terms.size(); i++) {
            if (termFrecuencies.containsKey(terms.get(i))) {
                frecuency = termFrecuencies.get(terms.get(i)) + 1;
            } else {
                frecuency = 1;
            }

            termFrecuencies.put(terms.get(i), frecuency);
        }

        return termFrecuencies;
    }
}
