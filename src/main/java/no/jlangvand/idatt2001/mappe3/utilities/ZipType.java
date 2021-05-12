package no.jlangvand.idatt2001.mappe3.utilities;

/**
 * Types of zip codes.
 */
public enum ZipType {

  /**
   * Street addresses.
   */
  ADDRESS("Street Address"),

  /**
   * PO Box.
   */
  BOX("PO Box"),

  /**
   * Both street addresses and PO Box.
   */
  ADDRESS_AND_BOX("Street Address and PO Box"),

  /**
   * Service code, not for use in actual addresses.
   */
  SERVICE("Service number"),

  /**
   * Multiple uses.
   */
  MULTI("Multiple uses"),

  /**
   * Unknown type.
   */
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
