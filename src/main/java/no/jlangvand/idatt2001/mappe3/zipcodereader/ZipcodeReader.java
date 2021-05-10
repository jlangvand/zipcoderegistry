package no.jlangvand.idatt2001.mappe3.zipcodereader;

import no.jlangvand.idatt2001.mappe3.model.NorwegianZipcode;

import java.util.List;

public interface ZipcodeReader {
  List<NorwegianZipcode> readAll() throws ZipCodeReaderException;
}
