package no.jlangvand.idatt2001.mappe3.view;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import no.jlangvand.idatt2001.mappe3.controller.MainController;
import no.jlangvand.idatt2001.mappe3.factories.TableFactory;
import no.jlangvand.idatt2001.mappe3.model.Zipcode;

public class MainView {

  private final MainController controller;
  private final Stage stage;
  private final TableView<Zipcode> tableView;

  public MainView(MainController controller, Stage stage) {
    this.controller = controller;
    this.stage = stage;

    var borderPane = new BorderPane();
    tableView = TableFactory.getTableView(controller.getZipcodes());
    tableView.getItems().addAll(controller.getZipcodes());

    borderPane.setCenter(tableView);
    stage.setScene(new Scene(borderPane));
  }

  public void show() {
    stage.show();
  }

  public void close() {
    stage.close();
  }

}
