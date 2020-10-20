package practica1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejer14 {
  private static String REGEX;
  private static String INPUT;

  public static void main(String[] args) {
    URL url;
    InputStream is = null;
    BufferedReader br;
    String line;
    String webHTML = "";

    try {
      url = new URL("https://www.uca.es/");
      is = url.openStream(); // throws an IOException
      br = new BufferedReader(new InputStreamReader(is));

      while ((line = br.readLine()) != null) {
        webHTML += line + "\n";
      }
    } catch (MalformedURLException mue) {
      mue.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } finally {
      try {
        if (is != null) is.close();
      } catch (IOException ioe) {
        // nothing to see here
      }
    }

    INPUT = webHTML;
    REGEX = "(<img.*src.*>)";

    Pattern pat = Pattern.compile(REGEX);
    Matcher mat = pat.matcher(INPUT);

    if (!mat.find()) {
      System.out.println("Match not found");
    } else {
      while (mat.find()) {
        System.out.println(mat.group() + " Match found");
      }
    }
  }
}
