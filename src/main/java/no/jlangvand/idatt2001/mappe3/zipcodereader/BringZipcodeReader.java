/*
 *     Copyright © 2021. Joakim Skogø Langvand <jlangvand@gmail.com>
 *
 *     This file is part of Zip Code Registry.
 *
 *     Zip Code Registry is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Zip Code Registry is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Zip Code Registry.  If not, see <https://www.gnu.org/licenses/>.
 */
package no.jlangvand.idatt2001.mappe3.zipcodereader;

import no.jlangvand.idatt2001.mappe3.app.App;
import no.jlangvand.idatt2001.mappe3.model.NorwegianZipcode;
import no.jlangvand.idatt2001.mappe3.model.Zipcode;

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

  private static String getZipType(String type) {
    return switch (type) {
      case "B" -> "Street addr. and PO box";
      case "F" -> "Multiple uses";
      case "G" -> "Street addr.";
      case "P" -> "PO box";
      case "S" -> "Service (not postal address)";
      default -> "Unknown";
    };
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Zipcode> readAll() {
    var zipCodes = new ArrayList<Zipcode>();
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
