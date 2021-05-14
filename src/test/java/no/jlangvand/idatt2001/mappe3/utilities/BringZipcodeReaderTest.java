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
package no.jlangvand.idatt2001.mappe3.utilities;

import no.jlangvand.idatt2001.mappe3.zipcodereader.BringZipcodeReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BringZipcodeReaderTest {

  private static final String REGISTRY_URL = "https://bit.ly/pnrreg";
  private static final File REGISTRY_FILE = new File(
      Thread.currentThread().getContextClassLoader().getResource("bringreg").getPath());

  /*
   The reader class will attempt to open a stream upon creation, thus it can be tested simply by
   instantiating it.
   */

  @Test
  @DisplayName("Instantiate a reader from URL, should not throw exception")
  void testInstantiatingFromURL() {
    assertDoesNotThrow(() -> new BringZipcodeReader(REGISTRY_URL));
  }

  @Test
  @DisplayName("Instantiate a reader from file, should not throw exception")
  void testInstantiationFromFile() {
    assertDoesNotThrow(() -> new BringZipcodeReader(REGISTRY_FILE));
  }

  /*
   When reading from the registry file, it should be enough to check that the returned list is not
   empty.
   */

  @Test
  @DisplayName("Read registry from reader and check that the list is not empty")
  void readAll() {
    assertTrue((new BringZipcodeReader(REGISTRY_FILE)).readAll().size() > 1);
  }

}