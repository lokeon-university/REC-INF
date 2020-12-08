package RI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class RI {

    private static File corpus = new File("corpus");

    public static void indexation() throws IOException {
        Filters filter = new Filters();
        Stemming stemming = new Stemming();
        ArrayList<String> filteredText;
        String text = "", str;

        //Filters
        for (File f : corpus.listFiles()) {
            BufferedReader br = new BufferedReader(new FileReader(f));
            while ((str = br.readLine()) != null) text += str;

            //Filtering characters...
            text = filter.filterCharacters(text);

            //Filtering stopwords...
            filteredText = new ArrayList<String>(Arrays.asList(text.split(" ")));
            filteredText = filter.filterStopwords(filteredText);

            //Stemming...
            filteredText = stemming.stemming(filteredText);

            //Filtereing by threshold...
            filteredText = filter.filterThreshold(filteredText);

            for (int i = 0; i < filteredText.size(); i++) {
                System.out.println(filteredText.get(i));
            }

            br.close();
        }
    }

    public static void main(String[] args) throws IOException {
        indexation();
    }
}
