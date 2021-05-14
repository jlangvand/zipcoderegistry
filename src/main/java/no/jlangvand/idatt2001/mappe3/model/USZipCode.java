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

public class USZipCode extends Zipcode {

  /**
   * Two letter code denoting state.
   */
  @TableProperty(label = "State", order = 10)
  public final String state;

  public USZipCode(String zip, String area, String state) {
    if (!zip.matches("\\d{5}"))
      throw new IllegalArgumentException("Zip code must be five digits");
    if (!state.toUpperCase().matches("[A-Z]{2}"))
      throw new IllegalArgumentException("State must be a two letter code");
    this.zip = zip;
    this.area = area.toUpperCase();
    this.state = state.toUpperCase();
  }

  public String getState() {
    return state;
  }

  @Override
  public String getAsString() {
    return "%s %s %s".formatted(getArea(), getState(), getZip());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean anyParameterContains(CharSequence s) {
    for (var str : s.toString().strip().toUpperCase().split("[^\\w]")) {
      if (!(getZip().startsWith(str)
          || getArea().startsWith(str)
          || getState().equals(str)))
        return false;
    }
    return true;
  }

}
