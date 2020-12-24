package RI;

import java.io.IOException;
import java.util.Scanner;

public class RI {

    private static Indexation indexation = new Indexation();
    private static Search search = new Search();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("-----------------------------------------------");
        int operation, results;
        boolean xt = false;
        String terms;

        while (!xt) {
            System.out.println("Choose what operation do you want to do: \n 1- Indexation\n 2- Search\n 0- Exit\n");
            operation = scanner.nextInt();
            switch (operation) {
                case 0:
                    xt = true;

                    break;
                case 1:
                    long init = System.currentTimeMillis();

                    indexation.start();

                    long end = System.currentTimeMillis() - init;

                    System.out.println("The duration of indexing was " + end + " ms.\n");

                    break;
                case 2:
                    System.out.println("\nWrite the terms that you want to search: ");
                    scanner.nextLine();
                    terms = scanner.nextLine();

                    System.out.println("\nHow many results do you want?");
                    results = scanner.nextInt();

                    System.out.println("Searching...\n");
                    search.start(terms, results);

                    System.out.println("\n----------------------------------------------------\n");

                    break;
                default:
                    System.out.println("There is not option available.");
                    break;
            }
        }
    }
}
