package no.jlangvand.idatt2001.mappe3.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import no.jlangvand.idatt2001.mappe3.model.Zipcode;
import no.jlangvand.idatt2001.mappe3.model.ZipcodeRegistry;

/**
 * Application controller.
 */
public class MainController {

  private final ObservableList<Zipcode> zipcodes;

  /**
   * Create a controller given a Zipcode registry.
   *
   * @param zipcodes Zipcodes to display
   * @param <T>      type of zipcodes, normally inferred from zipcodes parameter
   */
  public <T extends Zipcode> MainController(ZipcodeRegistry<T> zipcodes) {
    this.zipcodes = FXCollections.observableArrayList(zipcodes);
  }

  /**
   * Get list of all zipcodes.
   *
   * @return list of all zipcodes
   */
  public ObservableList<Zipcode> getZipcodes() {
    return zipcodes;
  }

  /**
   * Get a filtered list of Zipcodes.
   *
   * @param filter characters to search for
   * @return list of Zipcodes matching search criteria
   */
  public ObservableList<Zipcode> getZipcodes(CharSequence filter) {
    return zipcodes.filtered(zipcode -> zipcode.anyParameterContains(filter));
  }

}
