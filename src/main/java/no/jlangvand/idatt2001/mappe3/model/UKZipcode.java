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

public class UKZipcode extends Zipcode {

  /**
   * Two letter code denoting state.
   */
  @TableProperty(label = "Town", order = 10)
  public final String county;

  public UKZipcode(String zip, String area, String county) {
    if (!zip.matches("\\w{3} ?\\w{3}"))
      throw new IllegalArgumentException("Zip code must be six characters");
    this.zip = zip;
    this.area = area.toUpperCase();
    this.county = county.toUpperCase();
  }

  public String getCounty() {
    return county;
  }

  @Override
  public String getAsString() {
    return "%s%n%s%n%s".formatted(getArea(), getCounty(), getZip());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean anyParameterContains(CharSequence s) {
    for (var str : s.toString().strip().toUpperCase().split("[^\\w]")) {
      if (!(getZip().startsWith(str)
          || getArea().startsWith(str)
          || getCounty().startsWith(str)))
        return false;
    }
    return true;
  }

}
