package no.jlangvand.idatt2001.mappe3.app;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import no.jlangvand.idatt2001.mappe3.controller.MainController;
import no.jlangvand.idatt2001.mappe3.view.MainView;
import no.jlangvand.idatt2001.mappe3.zipcodereader.BringZipcodeReader;
import no.jlangvand.idatt2001.mappe3.zipcodereader.ZipCodeReaderException;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.ERROR;

/**
 * Main application class.
 */
public class App extends Application {

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
  private static final Logger LOGGER = Logger.getLogger(App.class.getName());
  private static final ButtonType BUTTON_DOWNLOAD = new ButtonType("Download");
  private static final ButtonType BUTTON_OPEN_FILE = new ButtonType("Choose File");
  private static final String APP_TITLE = "Zip Code Registry";

  /**
   * Main method launches application.
   *
   * @param args launch arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  private static boolean handleResponse(Stage stage, ButtonType bt) {
    MainController controller = null;
    try {
      if (bt == BUTTON_DOWNLOAD) controller = new MainController(
          new BringZipcodeReader(App.REGISTRY_URL).readAll());
      else if (bt == BUTTON_OPEN_FILE) controller = new MainController(
          new BringZipcodeReader((new FileChooser().showOpenDialog(stage))).readAll());
      if (Objects.nonNull(controller)) (new MainView(controller, stage)).show();
    } catch (NullPointerException ignore) {
      // No file selected from file chooser, just ignore this and show the dialog again
      return false;
    } catch (ZipCodeReaderException e) {
      exceptionHandler("Failed to open file: %s".formatted(e.getMessage()));
    }
    return true;
  }

  private static void exceptionHandler(String message, ButtonType... buttons) {
    LOGGER.log(SEVERE, () -> "Exception handler: %s".formatted(message));
    (new Alert(ERROR, message, buttons)).showAndWait();
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle(APP_TITLE);
    var dialog = new Alert(CONFIRMATION,
        "Automatically download latest Zip code file from Bring?",
        BUTTON_DOWNLOAD, BUTTON_OPEN_FILE, ButtonType.CLOSE);
    dialog.setTitle(APP_TITLE);
    dialog.setHeaderText("Download File?");
    Supplier<ButtonType> loadDialog = () -> dialog.showAndWait().orElse(ButtonType.CLOSE);
    while (!handleResponse(primaryStage, loadDialog.get())) ;
  }

}
