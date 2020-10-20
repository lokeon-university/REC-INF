package practica1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejer8 {
  private static String REGEX;
  private static String INPUT;

  public static void main(String[] args) {
    INPUT = "www.uca.es";
    REGEX = "^w{3}.*\\bes$";

    Pattern pat = Pattern.compile(REGEX);
    Matcher mat = pat.matcher(INPUT);

    if (mat.matches()) {
      System.out.println(INPUT + " Match found");
    } else {
      System.out.println(INPUT + " Match not found");
    }
  }
}
