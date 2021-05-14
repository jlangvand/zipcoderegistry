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
package no.jlangvand.idatt2001.mappe3.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for denoting fields to be shown as table columns.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableProperty {

  /**
   * Human readable label for table column.
   *
   * @return label
   */
  String label();

  /**
   * Order in which this column should be placed.
   *
   * <p>Columns will be sorted by this parameter. Sorting order is undefined if several columns
   * are numbered the same.
   *
   * @return order
   */
  int order();

}
