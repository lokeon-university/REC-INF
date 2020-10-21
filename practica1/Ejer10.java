package practica1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejer10 {
  private static String REGEX;

  public static void main(String[] args) {
    REGEX = "\\d{2,3}.\\d{2,3}.\\d{1,3}.\\d{1,3}";

    String[] ips = { "192.168.1.1", "200.36.127.40", "10.128.1.253" };
    for (int i = 0; i < ips.length; i++) {
      String str = ips[i];
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
