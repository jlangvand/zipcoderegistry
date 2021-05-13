package no.jlangvand.idatt2001.mappe3.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import no.jlangvand.idatt2001.mappe3.model.Zipcode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Application controller.
 */
public class MainController {

  private final List<Zipcode> zipcodes;
  private final ObservableList<Zipcode> observableZipcodes;

  /**
   * Create a controller given a Zipcode registry.
   *
   * @param zipcodes Zipcodes to display
   */
  public MainController(List<Zipcode> zipcodes) {
    // Using an unmodifiable list for space efficiency and to prevent accidental modifications.
    this.zipcodes = List.copyOf(zipcodes);
    this.observableZipcodes = FXCollections.observableArrayList(this.zipcodes);
  }

  /**
   * Get observable list of zip codes.
   *
   * @return observable list of zip codes
   */
  public ObservableList<Zipcode> getObservableZipcodes() {
    return observableZipcodes;
  }

  /**
   * Filter observable list upon key event.
   *
   * @param event key event
   */
  public void searchHandler(KeyEvent event) {
    var text = ((TextField) event.getSource()).getText();
    this.observableZipcodes.setAll(zipcodes.stream()
        .filter(zipcode -> zipcode.anyParameterContains(text))
        .collect(Collectors.toList()));
    event.consume();
  }

}
