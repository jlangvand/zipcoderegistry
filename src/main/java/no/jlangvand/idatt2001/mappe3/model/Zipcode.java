package no.jlangvand.idatt2001.mappe3.model;

import no.jlangvand.idatt2001.mappe3.annotations.TableProperty;

/**
 * Generic Zipcode, to be extended for local formats.
 */
public abstract class Zipcode {

  /**
   * Zip/postal code.
   */
  @TableProperty(label = "Zip Code", order = 0)
  public String zip;

  /**
   * Name of area the zip code represents.
   */
  @TableProperty(label = "Area Name", order = 1)
  public String area;

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
