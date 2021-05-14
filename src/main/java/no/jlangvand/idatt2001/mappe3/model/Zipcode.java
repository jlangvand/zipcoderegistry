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
package no.jlangvand.idatt2001.mappe3.model;

import no.jlangvand.idatt2001.mappe3.annotations.TableProperty;

/**
 * Generic Zipcode, to be extended for local formats.
 */
public abstract class Zipcode {

  /**
   * Zip/postal code.
   */
  @TableProperty(label = "Zip Code", order = 0)
  public String zip;

  /**
   * Name of area the zip code represents.
   */
  @TableProperty(label = "Area Name", order = 1)
  public String area;

  /**
   * Check if code contains given characters.
   *
   * <p>One can search for multiple fields at once, separated by any non-word character.
   *
   * @param s sequence to search for
   * @return true if Zipcode matches all terms
   */
  public abstract boolean anyParameterContains(CharSequence s);

  /**
   * Get zip code.
   *
   * @return zip code
   */
  public String getZip() {
    return zip;
  }

  /**
   * Get area name.
   *
   * @return area name
   */
  public String getArea() {
    return area;
  }

}
