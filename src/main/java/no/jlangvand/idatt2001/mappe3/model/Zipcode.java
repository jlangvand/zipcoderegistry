package no.jlangvand.idatt2001.mappe3.model;

/**
 * Generic Zipcode, to be extended for local formats.
 */
public abstract class Zipcode {

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
