package no.jlangvand.idatt2001.mappe3.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Norwegian Zipcode.
 */
public class NorwegianZipcode extends Zipcode {

  private static final List<String> labels;

  // Static initialiser to add our fields to the parent class' list
  static {
    var list = new ArrayList<String>();
    list.addAll(Zipcode.labels);
    list.addAll(List.of(
        "Municipality Name",
        "Municipality Code",
        "Type"));
    labels = list;
  }

  private final String municipalityCode;
  private final String municipalityName;
  private final String type;

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
      if (!(getZip().contains(str)
          || getArea().contains(str)
          || getMunicipalityCode().contains(str)
          || getMunicipalityName().contains(str))) {
        return false;
      }
    }
    return true;
  }

  @Override
  public List<String> getLabels() {
    return labels;
  }

}
