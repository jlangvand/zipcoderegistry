package no.jlangvand.idatt2001.mappe3.model;

import no.jlangvand.idatt2001.mappe3.utilities.ZipType;

import java.util.ArrayList;
import java.util.List;

public class NorwegianZipcode extends Zipcode {

  private static final List<String> labels;

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
  private final ZipType type;

  public NorwegianZipcode(String zipcode, String area, String municipalityCode, String municipalityName,
                          ZipType type) {
    this.zip = zipcode;
    this.area = area.toUpperCase();
    this.municipalityCode = municipalityCode;
    this.municipalityName = municipalityName.toUpperCase();
    this.type = type;
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
    s = s.toString().toUpperCase();
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
