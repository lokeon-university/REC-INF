package practica1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejer15 {
  private static String INPUT;

  public static void main(String[] args) {
    INPUT = "<a>uno</a><b>dos</b><c>tres</c><d>cuatro</d><e>cinco</e>";

    String[] regexs = {
      "<[^>]*>([^<]*)</[^>]*>",
      "<.*>(.*)<.*>",
      "<.*?>(.*?)<.*?>",
    };

    for (int i = 0; i < regexs.length; i++) {
      String regex = regexs[i];
      Pattern pat = Pattern.compile(regex);
      Matcher mat = pat.matcher(INPUT);
      System.out.println("\nRegex: " + regex + "\n");
      while (mat.find()) {
        System.out.println(mat.group(1));
      }
    }
  }
}
