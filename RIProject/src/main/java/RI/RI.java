package RI;

import java.io.IOException;

public class RI {

    private static Indexation indexation = new Indexation();
    private static Search search = new Search();

    public static void main(String[] args) throws IOException {
        indexation.start();
        search.start();
    }
}
