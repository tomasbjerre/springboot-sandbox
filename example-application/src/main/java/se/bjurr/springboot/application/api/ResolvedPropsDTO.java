package se.bjurr.springboot.application.api;

import java.util.List;

public class ResolvedPropsDTO {

  private final List<String> myStringListProperty;
  private final String myStringProperty;

  public ResolvedPropsDTO() {
    this.myStringListProperty = null;
    this.myStringProperty = null;
  }

  public ResolvedPropsDTO(final String myStringProperty, final List<String> myStringListProperty) {
    this.myStringProperty = myStringProperty;
    this.myStringListProperty = myStringListProperty;
  }

  public List<String> getMyStringListProperty() {
    return this.myStringListProperty;
  }

  public String getMyStringProperty() {
    return this.myStringProperty;
  }
}
