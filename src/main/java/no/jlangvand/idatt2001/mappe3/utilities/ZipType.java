package no.jlangvand.idatt2001.mappe3.utilities;

public enum ZipType {
  ADDRESS("Street Address"),
  BOX("PO Box"),
  ADDRESS_AND_BOX("Street Address and PO Box"),
  SERVICE("Service number"),
  MULTI("Multiple uses"),
  UNKNOWN("Other/Unknown");

  private final String label;

  ZipType(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
