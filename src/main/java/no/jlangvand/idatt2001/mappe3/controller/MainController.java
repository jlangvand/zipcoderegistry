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
public class MainController<T extends Zipcode> {

  private final List<T> zipcodes;
  private final ObservableList<Zipcode> observableZipcodes;

  /**
   * Create a controller given a Zipcode registry.
   *
   * @param zipcodes Zipcodes to display
   */
  public MainController(List<T> zipcodes) {
    this.zipcodes = zipcodes;
    this.observableZipcodes = FXCollections.observableArrayList(zipcodes);
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
