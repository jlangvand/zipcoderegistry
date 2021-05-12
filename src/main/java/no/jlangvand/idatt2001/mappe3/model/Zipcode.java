package no.jlangvand.idatt2001.mappe3.model;

import java.util.List;

/**
 * Generic Zipcode, to be extended for local formats.
 */
public abstract class Zipcode {

  /**
   * List of fields that should be read by the frontend.
   *
   * <p>Classes extending this class must append labels for fields they declare. Labels should be
   * in human readable format, and must match the declared field names.
   */
  protected static final List<String> labels = List.of("ZIP", "Area");

  /**
   * Zip/postal code.
   */
  protected String zip;

  /**
   * Name of area the zip code represents.
   */
  protected String area;

  /**
   * Check if code contains given characters.
   *
   * <p>One can search for multiple fields at once, separated by any non-word character.
   *
   * @param s sequence to search for
   * @return true if Zipcode matches all terms
   */
  public abstract boolean anyParameterContains(CharSequence s);

  /**
   * Get field names in human readable format.
   *
   * @return human readable field names
   */
  public List<String> getLabels() {
    return labels;
  }

  /**
   * Get zip code.
   *
   * @return zip code
   */
  public String getZip() {
    return zip;
  }

  /**
   * Get area name.
   *
   * @return area name
   */
  public String getArea() {
    return area;
  }

}
