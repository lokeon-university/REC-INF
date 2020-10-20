package practica1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejer11 {
  private static String REGEX;
  private static String INPUT;

  public static void main(String[] args) {
    INPUT = "+34 95 6030466";
    REGEX = "^\\+34\\s\\d{2}\\s\\d{7}$";

    Pattern pat = Pattern.compile(REGEX);
    Matcher mat = pat.matcher(INPUT);

    if (mat.matches()) {
      System.out.println(INPUT + " Match found");
    } else {
      System.out.println(INPUT + " Match not found");
    }
  }
}
