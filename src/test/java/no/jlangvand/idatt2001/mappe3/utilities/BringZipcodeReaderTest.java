package no.jlangvand.idatt2001.mappe3.utilities;

import no.jlangvand.idatt2001.mappe3.zipcodereader.BringZipcodeReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BringZipcodeReaderTest {

  private static final String REGISTRY_URL = "https://bit.ly/pnrreg";

  @Test
  void readFromURL() {
    assertDoesNotThrow(() -> new BringZipcodeReader(REGISTRY_URL));
  }

  @Test
  void readAll() {
    assertTrue((new BringZipcodeReader(REGISTRY_URL)).readAll().size() > 1);
  }

}