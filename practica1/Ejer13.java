package practica1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejer13 {
  private static String REGEX;
  public static void main(String[] args) {
    REGEX = "^v(\\w|!)(@|\\w).\\w(@|\\w)";

    String[] viagra = { "vi@gra", "v1agra", "v1@gra", "v!@gr@" };
    for (int i = 0; i < viagra.length; i++) {
      String str = viagra[i];
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
