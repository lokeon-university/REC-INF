package RI;

import RI.org.tartarus.snowball.ext.*;
import java.util.ArrayList;

public class Stemming {

    private englishStemmer stemmer = new englishStemmer();

    public ArrayList<String> stemming(ArrayList<String> terms) {
        for (int i = 0; i < terms.size(); i++) {
            if (!terms.get(i).contains("-")) {
                stemmer.setCurrent(terms.get(i));
                stemmer.stem();
                terms.set(i, stemmer.getCurrent());
            }
        }

        return terms;
    }
}
