package no.jlangvand.idatt2001.mappe3.view;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.jlangvand.idatt2001.mappe3.controller.MainController;
import no.jlangvand.idatt2001.mappe3.factories.TableFactory;
import no.jlangvand.idatt2001.mappe3.model.Zipcode;

/**
 * Class for handling the GUI.
 */
public class MainView {

  private final Stage stage;
  private final TableView<Zipcode> tableView;

  /**
   * Construct a new View.
   *
   * @param controller application controller
   * @param stage      stage to draw the GUI upon
   */
  public MainView(MainController controller, Stage stage) {
    this.stage = stage;

    var borderPane = new BorderPane();
    var centerVbox = new VBox();

    var searchBox = new TextField();
    centerVbox.getChildren().add(searchBox);

    tableView = TableFactory.getTableView(controller.getZipcodes());
    centerVbox.getChildren().add(tableView);

    searchBox.setPromptText("Filter by any parameter(s)");
    searchBox.setOnKeyTyped(keyEvent ->
        tableView.setItems(controller.getZipcodes(searchBox.getCharacters())));

    borderPane.setCenter(centerVbox);
    stage.setScene(new Scene(borderPane));
  }

  /**
   * Show the stage.
   */
  public void show() {
    stage.show();
  }

  /**
   * Close the stage.
   *
   * <p>Effectively closing the application if stage is the primary/only visible stage.
   */
  public void close() {
    stage.close();
  }

}
