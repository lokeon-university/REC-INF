package RI;

import RI.org.tartarus.snowball.ext.*;
import java.util.ArrayList;

public class Stemming {

    private englishStemmer stemmer = new englishStemmer();

    public ArrayList<String> stemming(ArrayList<String> filteredText) {
        for (int i = 0; i < filteredText.size(); i++) {
            if (!filteredText.get(i).contains("-")) {
                stemmer.setCurrent(filteredText.get(i));
                stemmer.stem();
                filteredText.set(i, stemmer.getCurrent());
            }
        }

        return filteredText;
    }
}
