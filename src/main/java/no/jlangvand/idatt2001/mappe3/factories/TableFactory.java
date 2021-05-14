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
package no.jlangvand.idatt2001.mappe3.factories;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import no.jlangvand.idatt2001.mappe3.annotations.TableProperty;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.logging.Logger;

import static java.util.logging.Level.WARNING;

/**
 * Factory class for producing a TableView from an ObservableList.
 */
public class TableFactory {

  private static final Logger LOGGER = Logger.getLogger(TableFactory.class.getName());

  private TableFactory() {
    // Hide implicit constructor
  }

  /**
   * Get a TableView for an ObservableList.
   *
   * <p>Columns will be created for fields annotated with TableProperty. Note that the list must
   * not be empty.
   *
   * @param list observable list containing objects annotated with TableProperty. This list must
   *             contain one or more elements.
   * @param <T>  type parameter of the list, normally inferred
   * @return TableView with generated rows and columns from the ObservableList
   * @throws NullPointerException if called with an empty list
   * @see TableProperty
   */
  public static <T> TableView<T> getTableView(ObservableList<T> list) {
    var table = new TableView<T>();
    table.getColumns().setAll(createColumnsFromList(list));
    table.setItems(list);
    return table;
  }

  private static <T> ArrayList<TableColumn<T, String>> createColumnsFromList(List<T> list) {
    var columns = new ArrayList<TableColumn<T, String>>();
    Arrays.stream(list.get(0).getClass().getFields())
        .filter(f -> f.isAnnotationPresent(TableProperty.class))
        .sorted(Comparator.comparingInt(TableFactory::getAnnotatedOrder))
        .forEachOrdered(field -> columns.add(createColumnFromField(field, list)));
    return columns;
  }

  private static <T> TableColumn<T, String> createColumnFromField(Field field, List<T> list) {
    var label = field.getAnnotation(TableProperty.class).label();
    var column = new TableColumn<T, String>(label);
    column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
    column.setPrefWidth(Math.max(calculateWidth(list, field), calculateWidth(label)));
    return column;
  }

  /*
  This might not be very elegant, but TableView lacks the ability to automatically adjust column
  width to fit the content.
   */
  private static <T> double calculateWidth(List<T> list, Field field) {
    ToDoubleFunction<T> getWidthOfStringField = t -> {
      try {
        return calculateWidth((String) field.get(t));
      } catch (IllegalAccessException ignored) {
        // This exception is not critical, so we just log and otherwise ignore it
        LOGGER.log(WARNING, () ->
            "Failed to read field %s from element %s".formatted(field.getName(), t.toString()));
        return 0.0;
      }
    };
    return list.stream().mapToDouble(getWidthOfStringField).max().orElse(100);
  }

  private static double calculateWidth(String s) {
    return (new Text(s)).getLayoutBounds().getWidth() + 30;
  }

  private static int getAnnotatedOrder(Field f) {
    return f.getAnnotation(TableProperty.class).order();
  }

}
