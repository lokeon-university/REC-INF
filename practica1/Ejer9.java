package practica1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejer9 {
  private static String REGEX;

  public static void main(String[] args) {
    REGEX = "^\\d{1,2}/\\d{1,2}/\\d{2}$";

    String[] dates = { "25/10/83", "4/11/56", "30/6/71", "4/3/85" };
    for (int i = 0; i < dates.length; i++) {
      String str = dates[i];
      Pattern pat = Pattern.compile(REGEX);
      Matcher mat = pat.matcher(str);
      if (mat.matches()) {
        System.out.println(str + " " + "Match found");
      } else {
        System.out.println(str + " " + "Match not found");
      }
    }
  }
}
