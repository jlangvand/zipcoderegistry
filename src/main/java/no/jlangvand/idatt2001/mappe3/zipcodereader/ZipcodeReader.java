package no.jlangvand.idatt2001.mappe3.zipcodereader;

import no.jlangvand.idatt2001.mappe3.model.Zipcode;

import java.util.List;

/**
 * Generic interface describing a class for reading zip codes.
 */
public interface ZipcodeReader {

  /**
   * Read all zip codes from the reader's source.
   *
   * @param <T> type of Zipcode
   * @return list of zipcodes
   * @throws ZipCodeReaderException if operation fails
   */
  <T extends Zipcode> List<T> readAll() throws ZipCodeReaderException;

}
