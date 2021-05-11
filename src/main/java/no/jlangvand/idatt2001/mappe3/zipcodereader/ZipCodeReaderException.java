package no.jlangvand.idatt2001.mappe3.zipcodereader;

/**
 * Generic zip code reader exception.
 *
 * <p>Descriptive message should be provided by the caller.
 */
public class ZipCodeReaderException extends RuntimeException {

  /**
   * Create a new exception.
   *
   * @param s descriptive message
   */
  public ZipCodeReaderException(String s) {
    super(s);
  }

}
