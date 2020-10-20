package practica1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejer7 {
  private static String REGEX;
  private static String INPUT;

  //ESTA MAL
  public static void main(String[] args) {
    INPUT = "alalal25lal26al";
    REGEX = "^.*2[^6].*$";

    Pattern pat = Pattern.compile(REGEX);
    Matcher mat = pat.matcher(INPUT);

    if (mat.matches()) {
      System.out.println(INPUT + " Match found");
    } else {
      System.out.println(INPUT + " Match not found");
    }
  }
}
