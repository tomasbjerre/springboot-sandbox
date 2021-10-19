package se.bjurr.springboot.application;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.bjurr.springboot.application.api.ResolvedPropsDTO;
import se.bjurr.springboot.properties.annotations.ExampleProperty;

@RestController
public class ExampleController {
  @ExampleProperty("se.bjurr.property.string")
  private String myStringProperty;

  @ExampleProperty("se.bjurr.property.liststring")
  private List<String> myStringListProperty;

  @GetMapping("/ping")
  public ResolvedPropsDTO ping() {
    return new ResolvedPropsDTO(this.myStringProperty, this.myStringListProperty);
  }
}
