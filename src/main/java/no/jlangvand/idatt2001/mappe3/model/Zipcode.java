package no.jlangvand.idatt2001.mappe3.model;

import java.util.List;

public abstract class Zipcode {

  protected static final List<String> labels = List.of("ZIP", "Area");

  protected String zip;
  protected String area;

  public abstract boolean anyParameterContains(CharSequence s);

  public List<String> getLabels() {
    return labels;
  }

  public String getZip() {
    return zip;
  }

  public String getArea() {
    return area;
  }

}
