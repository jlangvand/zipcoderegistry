package no.jlangvand.idatt2001.mappe3.utilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BringZipcodeReaderTest {

  @Test
  void readFromURL() {
    assertDoesNotThrow((ThrowingSupplier<BringZipcodeReader>) BringZipcodeReader::new);
  }

  @Test
  void readAll() {
    assertTrue((new BringZipcodeReader()).readAll().size() > 1);
  }

}