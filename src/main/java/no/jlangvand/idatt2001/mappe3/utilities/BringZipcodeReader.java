package no.jlangvand.idatt2001.mappe3.utilities;

import no.jlangvand.idatt2001.mappe3.model.NorwegianZipcode;
import no.jlangvand.idatt2001.mappe3.model.ZipcodeRegistry;
import no.jlangvand.idatt2001.mappe3.zipcodereader.ZipCodeReaderException;
import no.jlangvand.idatt2001.mappe3.zipcodereader.ZipcodeReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class BringZipcodeReader implements ZipcodeReader {

  private static final Logger LOGGER = Logger.getLogger(BringZipcodeReader.class.getName());
  private static final String RETRIEVE_FAILED_MSG = "Failed to retrieve registry file";

  private final InputStreamReader inputStreamReader;

  public BringZipcodeReader(String fileURL) throws ZipCodeReaderException {
    try {
      var url = new URL(fileURL);
      inputStreamReader = new InputStreamReader(url.openStream());
    } catch (IOException e) {
      var message = RETRIEVE_FAILED_MSG;
      LOGGER.log(SEVERE, message);
      throw new ZipCodeReaderException(message);
    }
  }

  @Override
  public ZipcodeRegistry<NorwegianZipcode> readAll() {
    var zipCodes = new ZipcodeRegistry<NorwegianZipcode>();
    try (var bufferedReader = new BufferedReader(inputStreamReader)) {
      for (var line = ""; (line = bufferedReader.readLine()) != null; ) {
        var row = line.split("\t");
        if (row.length != 5)
          throw new ZipCodeReaderException("Invalid file (wrong number of fields)");
        zipCodes.add(new NorwegianZipcode(row[0], row[1], row[2], row[3], getZipType(row[4])));
      }
    } catch (IOException e) {
      var message = "IO error while reading registry file";
      LOGGER.log(SEVERE, message);
      throw new ZipCodeReaderException(message);
    }
    return zipCodes;
  }

  private static ZipType getZipType(String type) {
    return switch (type) {
      case "B" -> ZipType.ADDRESS_AND_BOX;
      case "F" -> ZipType.MULTI;
      case "G" -> ZipType.ADDRESS;
      case "P" -> ZipType.BOX;
      case "S" -> ZipType.SERVICE;
      default -> ZipType.UNKNOWN;
    };
  }

}
