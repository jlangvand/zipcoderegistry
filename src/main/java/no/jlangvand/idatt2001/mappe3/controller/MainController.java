package no.jlangvand.idatt2001.mappe3.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import no.jlangvand.idatt2001.mappe3.model.Zipcode;
import no.jlangvand.idatt2001.mappe3.model.ZipcodeRegistry;

public class MainController {

  private ObservableList<Zipcode> zipcodes;

  public <T extends Zipcode> MainController(ZipcodeRegistry<T> zipcodes) {
    this.zipcodes = FXCollections.observableArrayList(zipcodes);
  }

  public ObservableList<Zipcode> getZipcodes() {
    return zipcodes;
  }

  public ObservableList<Zipcode> getZipcodes(CharSequence filter) {
    return zipcodes.filtered(zipcode -> zipcode.anyParameterContains(filter));
  }

}
