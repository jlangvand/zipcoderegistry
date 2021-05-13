package no.jlangvand.idatt2001.mappe3.model;

import no.jlangvand.idatt2001.mappe3.annotations.TableProperty;

/**
 * Norwegian Zipcode implementation.
 *
 * <p>Annotated with {@link TableProperty} so this class can be used with
 * {@link no.jlangvand.idatt2001.mappe3.factories.TableFactory}.
 *
 * @see Zipcode
 * @see no.jlangvand.idatt2001.mappe3.factories.TableFactory
 * @see TableProperty
 */
public class NorwegianZipcode extends Zipcode {

  /**
   * Denotes county and municipality.
   */
  @TableProperty(label = "Municipality Code", order = 11)
  public final String municipalityCode;

  /**
   * Name of municipality.
   */
  @TableProperty(label = "Municipality Name", order = 10)
  public final String municipalityName;

  /**
   * Type of Zip code.
   */
  @TableProperty(label = "ZIP Type", order = 12)
  public final String type;

  /**
   * Create a new Zipcode.
   *
   * @param zipcode          four digit Zip code
   * @param area             name of area the code belongs to
   * @param municipalityCode four digit municipality code, county name can be derived from this
   *                         code
   * @param municipalityName name of municipality
   * @param type             type of Zip code (street address, PO box, etc)
   */
  public NorwegianZipcode(String zipcode, String area, String municipalityCode,
                          String municipalityName, String type) {
    if (!zipcode.matches("\\d{4}")
        || !municipalityCode.matches("\\d{4}")) {
      throw new IllegalArgumentException(
          "Invalid parameters; zip and municipality code must be four digits");
    }
    this.zip = zipcode;
    this.area = area.toUpperCase();
    this.municipalityCode = municipalityCode;
    this.municipalityName = municipalityName.toUpperCase();
    this.type = type;
  }

  /**
   * Get municipality code.
   *
   * @return municipality code
   */
  public String getMunicipalityCode() {
    return municipalityCode;
  }

  /**
   * Get municipality name.
   *
   * @return municipality name
   */
  public String getMunicipalityName() {
    return municipalityName;
  }

  /**
   * Get zip type.
   *
   * @return zip type
   */
  public String getType() {
    return type;
  }

  @Override
  public boolean anyParameterContains(CharSequence s) {
    for (var str : s.toString().strip().toUpperCase().split("[^\\wÆØÅ]")) {
      if (!(getZip().startsWith(str)
          || getArea().startsWith(str)
          || getMunicipalityCode().startsWith(str)
          || getMunicipalityName().startsWith(str))) {
        return false;
      }
    }
    return true;
  }

}
