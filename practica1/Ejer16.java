package practica1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejer16 {

  public static void main(String[] args) throws IOException {
    File file = new File("practica1/EjercicioExpresiones.txt");
    BufferedReader br = new BufferedReader(new FileReader(file));
    String text = "";
    String str;
    String[] symbols = {
      ":",
      ",",
      "\\.",
      ";",
      "\\?",
      "¿",
      "¡",
      "!",
      "\\.\\.\\.",
      "\"",
      "'",
      "<<",
      ">>",
    };

    while ((str = br.readLine()) != null) text += str;

    for (int i = 0; i < symbols.length; i++) {
      text = text.replaceAll(symbols[i], "");
    }

    br.close();

    System.out.println(text);
  }
}
