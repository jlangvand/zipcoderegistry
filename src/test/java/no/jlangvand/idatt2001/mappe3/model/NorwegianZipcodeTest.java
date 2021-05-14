/*
 *     Copyright © 2021. Joakim Skogø Langvand <jlangvand@gmail.com>
 *
 *     This file is part of Zip Code Registry.
 *
 *     Zip Code Registry is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Zip Code Registry is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Zip Code Registry.  If not, see <https://www.gnu.org/licenses/>.
 */
package no.jlangvand.idatt2001.mappe3.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NorwegianZipcodeTest {

  static NorwegianZipcode zipcode = new NorwegianZipcode("0123", "Oslo", "1234", "Oslo", "B");

  /*
   Test getters
   */

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

  /*
   Test the matching method used for searching.
   */
  @Test
  @DisplayName("Should return true if and only if a searchable parameter starts with string")
  void anyParameterContains() {
    assertTrue(zipcode.anyParameterContains("os"));
    assertTrue(zipcode.anyParameterContains("01"));
    assertFalse(zipcode.anyParameterContains("lo"));
    assertFalse(zipcode.anyParameterContains("23"));
  }

  /*
   * A Norwegian zip code consists of exactly four digits.
   *
   * The constructor should throw an IllegalArgumentException if other values are provided.
   */
  @Test
  @DisplayName("Test sanity check of zip code parameter")
  void testZipSanityCheck() {
    assertThrows(IllegalArgumentException.class, () ->
        new NorwegianZipcode("12345", "", "", "", ""));
    assertThrows(IllegalArgumentException.class, () ->
        new NorwegianZipcode("abc", "", "", "", ""));
  }

}