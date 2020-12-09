package RI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Filters {

    private ArrayList<String> stopwords = new ArrayList<String>();
    private String sps;
    BufferedReader sp = new BufferedReader(new FileReader("stopwords.txt"));

    public Filters() throws IOException {
        while ((sps = sp.readLine()) != null) stopwords.add(sps);
        sp.close();
    }

    public ArrayList<String> filterThreshold(ArrayList<String> terms) {
        for (int i = 0; i < terms.size(); i++) {
            if (terms.get(i).length() < 2) {
                terms.remove(i);
            }
        }

        terms.trimToSize();

        return terms;
    }

    public ArrayList<String> filterStopwords(ArrayList<String> terms) throws IOException {
        for (int i = 0; i < stopwords.size(); i++) {
            if (terms.contains(stopwords.get(i))) {
                terms.removeAll(Collections.singleton(stopwords.get(i)));
            }
        }

        terms.trimToSize();

        return terms;
    }

    public String filterCharacters(String text) {
        text = text.toLowerCase();
        text = text.replaceAll("[^-\\w]", " ");
        text = text.replaceAll("\\b[0-9]+\\b", " ");
        text = text.replaceAll("-+ | -+", " ");
        text = text.replaceAll(" +", " ");

        return text;
    }
}
