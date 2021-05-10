package no.jlangvand.idatt2001.mappe3.zipcodereader;

import no.jlangvand.idatt2001.mappe3.model.Zipcode;

import java.util.List;

public interface ZipcodeReader {
  List<Zipcode> readAll() throws ZipCodeReaderException;
}
