package no.jlangvand.idatt2001.mappe3.factories;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import no.jlangvand.idatt2001.mappe3.model.Zipcode;
import no.jlangvand.idatt2001.mappe3.utilities.StringUtils;

import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

public class TableFactory {

  private static final Logger LOGGER = Logger.getLogger(TableFactory.class.getName());

  private TableFactory() {
    // Hide implicit constructor
  }

  public static <T extends Zipcode> TableView<T> getTableView(ObservableList<T> registry) {
    // Not too happy about this solution. We need to be sure the registry is not empty at this point
    var labels = registry.get(0).getLabels();
    LOGGER.log(INFO, () -> "Creating table for class with labels " + labels);
    var table = new TableView<T>();
    labels.forEach(label -> {
      var c = new TableColumn<T, String>(label);
      c.setCellValueFactory(new PropertyValueFactory<>(StringUtils.toPascalCase(label)));
      c.setPrefWidth(calculateWidth(label));
      table.getColumns().add(c);
    });
    table.setItems(registry);
    return table;
  }

  // Not very elegant, but TableColumn lacks a (public) auto resize method
  private static double calculateWidth(String s) {
    return Math.max((new Text(s)).getLayoutBounds().getWidth() * 1.3, 60);
  }

}
