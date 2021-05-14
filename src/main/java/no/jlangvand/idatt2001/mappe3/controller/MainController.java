/*
 *     Copyright © 2021. Joakim Skogø Langvand <jlangvand@gmail.com>
 *
 *     This file is part of Zip Code Registry.
 *
 *     Zip Code Registry is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Zip Code Registry is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Zip Code Registry.  If not, see <https://www.gnu.org/licenses/>.
 */
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
