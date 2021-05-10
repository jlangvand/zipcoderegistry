package no.jlangvand.idatt2001.mappe3.utilities;

import java.util.Arrays;

public class StringUtils {
  private StringUtils() {
    // Hide implicit constructor
  }
  public static String toPascalCase(String s) {
    s = s.toLowerCase();
    return lowerCaseFirst(Arrays.stream(s.split(" ")).collect(
        StringBuilder::new,
        (a, b) -> a.append(capitalise(b.toLowerCase())),
        StringBuilder::append).toString());
  }

  public static String capitalise(String s) {
    return s.replaceFirst("\\w", s.substring(0, 1).toUpperCase());
  }

  private static String lowerCaseFirst(String s) {
    return s.replaceFirst("\\w", s.substring(0, 1).toLowerCase());  }
}
