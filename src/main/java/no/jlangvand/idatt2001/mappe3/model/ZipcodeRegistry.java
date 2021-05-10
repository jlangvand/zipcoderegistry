package no.jlangvand.idatt2001.mappe3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ZipcodeRegistry<T extends Zipcode> extends ArrayList<T> {

  public List<T> searchByAnyAttribute(String s) {
    return stream().filter(zipcode -> zipcode.anyParameterContains(s)).collect(Collectors.toList());
  }

}
