package no.jlangvand.idatt2001.mappe3.app;

import javafx.application.Application;
import javafx.stage.Stage;
import no.jlangvand.idatt2001.mappe3.controller.MainController;
import no.jlangvand.idatt2001.mappe3.utilities.BringZipcodeReader;
import no.jlangvand.idatt2001.mappe3.view.MainView;

/**
 * Main application class.
 */
public class App extends Application {

  /**
   * URL for Bring zip code file.
   */
  public static final String REGISTRY_URL = "https://bit.ly/pnrreg";

  /**
   * Main method launches application.
   *
   * @param args launch arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    var controller = new MainController(new BringZipcodeReader(REGISTRY_URL).readAll());
    var view = new MainView(controller, primaryStage);
    primaryStage.setWidth(700);
    view.show();
  }

}
