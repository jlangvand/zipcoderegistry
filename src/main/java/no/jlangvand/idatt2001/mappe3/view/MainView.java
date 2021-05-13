package no.jlangvand.idatt2001.mappe3.view;

import javafx.scene.Scene;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import no.jlangvand.idatt2001.mappe3.controller.MainController;
import no.jlangvand.idatt2001.mappe3.factories.TableFactory;

/**
 * Class for handling the GUI.
 */
public class MainView {

  private final Stage stage;

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
    searchBox.setPromptText("Filter by any parameter(s)");
    searchBox.setOnKeyTyped(controller::searchHandler);
    centerVbox.getChildren().add(searchBox);

    var tableView = TableFactory.getTableView(controller.getObservableZipcodes());
    centerVbox.getChildren().add(tableView);

    stage.setWidth(tableView.getColumns().stream().mapToDouble(TableColumnBase::getPrefWidth).sum() + 30);

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
