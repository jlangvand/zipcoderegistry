package no.jlangvand.idatt2001.mappe3.app;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.jlangvand.idatt2001.mappe3.controller.MainController;
import no.jlangvand.idatt2001.mappe3.zipcodereader.BringZipcodeReader;
import no.jlangvand.idatt2001.mappe3.view.MainView;
import no.jlangvand.idatt2001.mappe3.zipcodereader.ZipCodeReaderException;

import java.io.File;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.ERROR;

/**
 * Main application class.
 */
public class App extends Application {

  private static final Logger LOGGER = Logger.getLogger(App.class.getName());

  /**
   * URL for Bring zip code file.
   */
  public static final String REGISTRY_URL =
      "https://www.bring.no/postnummerregister-ansi.txt";

  /**
   * URL for info and manual download of registry file.
   */
  public static final String REGISTRY_INFO_URL =
      "https://www.bring.no/tjenester/adressetjenester/postnummer/";

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
    var downloadButton = new ButtonType("Download");
    var openFileButton = new ButtonType("Choose File");
    var dialog = new Alert(CONFIRMATION,
        "Automatically download zip code file from internet?",
        downloadButton, openFileButton, ButtonType.CLOSE);
    ButtonType selected;
    while ((selected = dialog.showAndWait().orElse(ButtonType.CLOSE)) != ButtonType.CLOSE) {
      try {
        if (selected == downloadButton || selected == openFileButton) {
          if (selected == downloadButton)
            showApplication(primaryStage, REGISTRY_URL);
          else
            showApplication(primaryStage, (new FileChooser().showOpenDialog(primaryStage)));
          break;
        }
      } catch (ZipCodeReaderException e) {
        exceptionHandler(e.getMessage());
      } catch (NullPointerException ignore) {
        /*
         Exception thrown when file chooser dialog is closed without choosing a file, and can be
         safely ignored.
        */
      }
    }
  }

  private static void showApplication(Stage stage, String path) {
    var controller = new MainController<>(new BringZipcodeReader(path).readAll());
    (new MainView(controller, stage)).show();
  }

  private static void showApplication(Stage stage, File file) {
    var controller = new MainController<>(new BringZipcodeReader(file).readAll());
    (new MainView(controller, stage)).show();
  }

  private void exceptionHandler(String message, ButtonType... buttons) {
    LOGGER.log(SEVERE, () -> "Exception handler: %s".formatted(message));
    (new Alert(ERROR, message, buttons)).showAndWait();
  }

}
