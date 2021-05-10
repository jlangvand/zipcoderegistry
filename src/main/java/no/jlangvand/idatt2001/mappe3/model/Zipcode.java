package no.jlangvand.idatt2001.mappe3.model;

import java.util.List;

public abstract class Zipcode {

  public abstract boolean anyParameterContains(CharSequence s);

  public abstract List<String> getLabels();

}
