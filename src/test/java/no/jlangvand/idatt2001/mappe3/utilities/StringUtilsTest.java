package no.jlangvand.idatt2001.mappe3.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilsTest {

  @Test
  void toPascalCase() {
    var stringA = "This is a Test";
    var stringB = "another test";
    var convertedA = StringUtils.toPascalCase(stringA);
    var convertedB = StringUtils.toPascalCase(stringB);
    var expectedA = "thisIsATest";
    var expectedB = "anotherTest";
    assertEquals(expectedA, convertedA);
    assertEquals(expectedB, convertedB);
  }

}