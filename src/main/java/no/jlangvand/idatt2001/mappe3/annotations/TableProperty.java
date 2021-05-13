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
