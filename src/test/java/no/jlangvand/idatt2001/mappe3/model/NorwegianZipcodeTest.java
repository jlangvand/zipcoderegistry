package no.jlangvand.idatt2001.mappe3.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NorwegianZipcodeTest {

  static NorwegianZipcode zipcode = new NorwegianZipcode("0123", "Oslo", "1234", "Oslo", "B");

  @Test
  void getMunicipalityCode() {
    assertEquals("0123", zipcode.getZip());
  }

  @Test
  void getMunicipalityName() {
    assertEquals("OSLO", zipcode.getMunicipalityName());
  }

  @Test
  void getType() {
    assertEquals("B", zipcode.getType());
  }

  @Test
  void anyParameterContains() {
    assertTrue(zipcode.anyParameterContains("os"));
    assertTrue(zipcode.anyParameterContains("01"));
    assertFalse(zipcode.anyParameterContains("lo"));
    assertFalse(zipcode.anyParameterContains("23"));
  }

  @Test
  void testZipSanityCheck() {
    assertThrows(IllegalArgumentException.class, () ->
        new NorwegianZipcode("12345", "", "", "", ""));
    assertThrows(IllegalArgumentException.class, () ->
        new NorwegianZipcode("abc", "", "", "", ""));
  }

}