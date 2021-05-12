package no.jlangvand.idatt2001.mappe3.factories;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
   * Get a TableView for an ObservableList of Zipcodes.
   *
   * @param registry list of Zipcodes, must not be empty
   * @param <T>      type of Zipcode, normally inferred from registry parameter
   * @return TableView of Zipcodes
   */
  public static <T> TableView<T> getTableView(ObservableList<T> registry) {
    var getters = getGettersFromObject(registry.stream().findFirst().orElseThrow());
    var table = new TableView<T>();
    getters.forEach(getter -> {
      if (!getter.getName().equals("getClass")) {
        var name = getter.getName().substring(3);
        var c = new TableColumn<T, String>(name);
        c.setCellValueFactory(new PropertyValueFactory<>(name));
        c.setPrefWidth(Math.max(calculateWidth(name), calculateWidth(registry, getter)));
        table.getColumns().add(c);
      }
    });
    table.setItems(registry);
    return table;
  }

  private static List<Method> getGettersFromObject(Object o) {
    /*
     This one is a little awkward, because we want to make sure we order the superclass'
     methods before the implementing class
    */
    var methods = Arrays.stream(
        o.getClass().getSuperclass().getDeclaredMethods()).collect(Collectors.toList());
    methods.addAll(Arrays.stream(o.getClass().getDeclaredMethods()).collect(Collectors.toList()));
    methods.removeIf(method -> !method.getName().startsWith("get"));
    return methods;
  }

  private static <T> double calculateWidth(ObservableList<T> list, Method getter) {
    // Let's just declare this as an array so we can mutate it from a lambda expression
    final var max = new double[]{60.0}; // Use 60 as minimum width
    list.forEach(o -> {
      try {
        max[0] = Math.max(max[0], calculateWidth((String) getter.invoke(o)));
      } catch (IllegalAccessException | InvocationTargetException | ClassCastException e) {
        // This method is not critical, so we'll log the exception but otherwise ignore it
        LOGGER.log(INFO, () -> "Failed to invoke method %s".formatted(getter.getName()));
      }
    });
    return max[0];
  }

  // Not very elegant, but TableColumn lacks a (public) auto resize method
  private static double calculateWidth(String s) {
    return Math.max((new Text(s)).getLayoutBounds().getWidth() * 1.3, 60);
  }

}
