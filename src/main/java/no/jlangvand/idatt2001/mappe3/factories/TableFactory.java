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
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

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
   * <p>Columns will be created for fields annotated with
   * TableProperty. Note that the list must not be empty.
   *
   * @param list observable list containing objects annotated with TableProperty. This list must
   *             contain one or more elements.
   * @param <T>  type parameter of the list parameter, normally inferred
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
        .sorted(Comparator.comparingInt(f -> f.getAnnotation(TableProperty.class).order()))
        .forEach(field -> {
          var label = field.getAnnotation(TableProperty.class).label();
          var column = new TableColumn<T, String>(label);
          column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
          column.setPrefWidth(Math.max(calculateWidth(list, field), calculateWidth(label)));
          columns.add(column);
        });
    return columns;
  }

  private static <T> double calculateWidth(List<T> list, Field field) {
    // Let's just declare this as an array so we can mutate it from a lambda expression. Using 60
    // as minimum width.
    final var max = new double[]{60.0};
    list.forEach(o -> {
      try {
        max[0] = Math.max(max[0], calculateWidth((String) field.get(o)));
      } catch (Exception ignored) {
        // This method is not critical, so we'll log the exception but otherwise ignore it
        LOGGER.log(INFO, () -> "Failed to invoke method %s".formatted(field.getName()));
      }
    });
    return max[0];
  }

  // Not very elegant, but TableProperty lacks a (public) auto resize method
  private static double calculateWidth(String s) {
    return Math.max((new Text(s)).getLayoutBounds().getWidth() + 30, 60);
  }

}
