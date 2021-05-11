package no.jlangvand.idatt2001.mappe3.zipcodereader;

import no.jlangvand.idatt2001.mappe3.model.Zipcode;

import java.util.List;

/**
 * Generic interface describing a class for reading zip codes.
 */
public interface ZipcodeReader {

  <T extends Zipcode> List<T> readAll() throws ZipCodeReaderException;

}
