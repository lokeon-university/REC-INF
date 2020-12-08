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

    public ArrayList<String> filterThreshold(ArrayList<String> filteredText) {
        for (int i = 0; i < filteredText.size(); i++) {
            if (filteredText.get(i).length() < 2) {
                filteredText.remove(i);
            }
        }

        filteredText.trimToSize();

        return filteredText;
    }

    public ArrayList<String> filterStopwords(ArrayList<String> filteredText) throws IOException {
        for (int i = 0; i < stopwords.size(); i++) {
            if (filteredText.contains(stopwords.get(i))) {
                filteredText.removeAll(Collections.singleton(stopwords.get(i)));
            }
        }

        filteredText.trimToSize();

        return filteredText;
    }

    public String filterCharacters(String text) {
        text = text.replaceAll("[^-\\w ]", "");
        text = text.replaceAll("^-|-$", " ");
        text = text.replaceAll("\\b[0-9]+\\b", " ");
        text = text.replaceAll(" +", " ");
        text = text.toLowerCase();

        return text;
    }
}
