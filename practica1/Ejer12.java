package practica1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejer12 {
  private static String REGEX;

  public static void main(String[] args) {
    String[] ps = {
      "P 12-34567",
      "P-12-3456",
      "P# 12 3456",
      "P#12-3456",
      "P 123456",
    };

    REGEX =
      "^P((#\\s?\\d{2}[\\s\\-]\\d{4})|([\\s\\-]\\d{2}\\-\\d{4,5})|(\\s\\d{6}))";

    for (int i = 0; i < ps.length; i++) {
      String str = ps[i];
      Pattern pat = Pattern.compile(REGEX);
      Matcher mat = pat.matcher(str);

      if (mat.matches()) {
        System.out.println(str + " Match found");
      } else {
        System.out.println(str + " Match not found");
      }
    }
  }
}
