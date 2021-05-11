package no.jlangvand.idatt2001.mappe3.app;

/**
 * Wrapper for the App class.
 *
 * <p>Workaround for "fat jar" dependency issue.
 */
public class Main {

  /**
   * Main method calls App.main.
   *
   * @param args launch arguments
   */
  public static void main(String[] args) {
    App.main(args);
  }

}
