package no.jlangvand.idatt2001.mappe3.utilities;

import no.jlangvand.idatt2001.mappe3.app.App;
import no.jlangvand.idatt2001.mappe3.model.NorwegianZipcode;
import no.jlangvand.idatt2001.mappe3.zipcodereader.ZipCodeReaderException;
import no.jlangvand.idatt2001.mappe3.zipcodereader.ZipcodeReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

/**
 * Read Norwegian Zipcodes from Bring.
 */
public class BringZipcodeReader implements ZipcodeReader {

  private static final Logger LOGGER = Logger.getLogger(BringZipcodeReader.class.getName());

  private final InputStreamReader reader;

  /**
   * Read Zipcodes from an URL.
   *
   * @param fileURL URL for Bring zip code file
   * @throws ZipCodeReaderException thrown if retrieval or read fails
   */
  public BringZipcodeReader(String fileURL) throws ZipCodeReaderException {
    try {
      var url = new URL(fileURL);
      reader = new InputStreamReader(url.openStream(), StandardCharsets.ISO_8859_1);
    } catch (IOException e) {
      var message = """
          Failed to download file from URL.
                    
          Registry file can be downloaded manually from %s
          """.formatted(App.REGISTRY_INFO_URL);
      LOGGER.log(SEVERE, message);
      throw new ZipCodeReaderException(message);
    }
  }

  /**
   * Read Zipcodes from a local file.
   *
   * @param file Bring zip code file
   * @throws ZipCodeReaderException thrown if retrieval or read fails
   */
  public BringZipcodeReader(File file) throws ZipCodeReaderException {
    try {
      reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1);
    } catch (IOException e) {
      var message = """
          Failed to read file, make sure the file is of the right type.
                
          Registry file can be downloaded from %s
          """.formatted(App.REGISTRY_INFO_URL);
      LOGGER.log(SEVERE, message);
      throw new ZipCodeReaderException(message);
    }
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

  @Override
  @SuppressWarnings("unchecked")
  public List<NorwegianZipcode> readAll() {
    var zipCodes = new ArrayList<NorwegianZipcode>();
    try (var bufferedReader = new BufferedReader(reader)) {
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

}
