package no.jlangvand.idatt2001.mappe3.model;

import no.jlangvand.idatt2001.mappe3.utilities.ZipType;

public class Zipcode {

  private final String code;
  private final String area;
  private final String municipalityCode;
  private final String municipalityName;
  private final ZipType type;

  public Zipcode(String zipcode, String area, String municipalityCode, String municipalityName,
                 ZipType type) {
    this.code = zipcode;
    this.area = area;
    this.municipalityCode = municipalityCode;
    this.municipalityName = municipalityName;
    this.type = type;
  }

  public String getCode() {
    return code;
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

  public ZipType getType() {
    return type;
  }

}
