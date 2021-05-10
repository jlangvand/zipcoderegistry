package no.jlangvand.idatt2001.mappe3.model;

import no.jlangvand.idatt2001.mappe3.utilities.ZipType;

import java.util.List;

public class NorwegianZipcode extends Zipcode {

  private final List<String> labels = List.of(
      "ZIP",
      "Area",
      "Municipality Code",
      "Municipality Name",
      "Type");

  private final String zip;
  private final String area;
  private final String municipalityCode;
  private final String municipalityName;
  private final ZipType type;

  public NorwegianZipcode(String zipcode, String area, String municipalityCode, String municipalityName,
                          ZipType type) {
    this.zip = zipcode;
    this.area = area;
    this.municipalityCode = municipalityCode;
    this.municipalityName = municipalityName;
    this.type = type;
  }

  public String getZip() {
    return zip;
  }

  public String getArea() {
    return area;
  }

  public String getMunicipalityCode() {
    return municipalityCode;
  }

  public String getMunicipalityName() {
    return municipalityName;
  }

  public String getType() {
    return type.toString();
  }

  @Override
  public boolean anyParameterContains(CharSequence s) {
    return getZip().contains(s)
        || getArea().contains(s)
        || getMunicipalityCode().contains(s)
        || getMunicipalityName().contains(s);
  }

  @Override
  public List<String> getLabels() {
    return labels;
  }

}
