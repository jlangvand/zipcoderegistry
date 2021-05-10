package no.jlangvand.idatt2001.mappe3.utilities;

import no.jlangvand.idatt2001.mappe3.model.NorwegianZipcode;
import no.jlangvand.idatt2001.mappe3.model.ZipcodeRegistry;
import no.jlangvand.idatt2001.mappe3.zipcodereader.ZipCodeReaderException;
import no.jlangvand.idatt2001.mappe3.zipcodereader.ZipcodeReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;

public class BringZipcodeReader implements ZipcodeReader {

  private static final Logger LOGGER = Logger.getLogger(BringZipcodeReader.class.getName());
  private static final String RETRIEVE_FAILED_MSG = "Failed to retrieve registry file from Bring";
  private static final URL BRING_REGISTRY_URL;

  private final InputStreamReader inputStreamReader;

  static {
    URL bringRegistryUrl;
    try {
      bringRegistryUrl = new URL("https://www.bring.no/tjenester/adressetjenester/postnummer/postnummertabeller-veiledning/_/attachment/download/7f0186f6-cf90-4657-8b5b-70707abeb789:c592731be13e0a0588de5a345917c96afd317022/Postnummerregister-ansi.txt");
    } catch (MalformedURLException e) {
      bringRegistryUrl = null;
      LOGGER.log(WARNING, RETRIEVE_FAILED_MSG);
    }
    BRING_REGISTRY_URL = bringRegistryUrl;
  }

  public BringZipcodeReader(String filePath) throws ZipCodeReaderException {
    try {
      inputStreamReader = new FileReader(filePath);
    } catch (FileNotFoundException e) {
      LOGGER.log(SEVERE, RETRIEVE_FAILED_MSG);
      throw new ZipCodeReaderException(RETRIEVE_FAILED_MSG);
    }
  }

  public BringZipcodeReader() throws ZipCodeReaderException {
    if (BRING_REGISTRY_URL == null)
      throw new ZipCodeReaderException(RETRIEVE_FAILED_MSG);
    try {
      inputStreamReader = new InputStreamReader(BRING_REGISTRY_URL.openStream());
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
