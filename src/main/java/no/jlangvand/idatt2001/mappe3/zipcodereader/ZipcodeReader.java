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
