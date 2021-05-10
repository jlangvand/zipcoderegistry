package no.jlangvand.idatt2001.mappe3.factories;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    var labels = registry.get(0).getLabels();
    LOGGER.log(INFO, () -> "Creating table for class with labels " + labels);
    var table = new TableView<T>();
    labels.forEach(label -> {
      var c = new TableColumn<T, String>(label);
      c.setCellValueFactory(new PropertyValueFactory<>(StringUtils.toPascalCase(label)));
      table.getColumns().add(c);
    });

    return table;
  }

}
