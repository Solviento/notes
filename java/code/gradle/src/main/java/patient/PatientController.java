package patient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

@Controller
public class PatientController {

  private PatientDS patientds = new PatientDS();

  @GetMapping("/members")
  public String viewMembers(Model model) {
    List<Greeting> list = patientds.getPatients();
    model.addAttribute("members", list);
    return "patienthistory";
  }

  @GetMapping("/greeting")
  public String greetingForm(Model model) {
    model.addAttribute("greeting", new Greeting());
    return "form";
  }

  @PostMapping("/greeting")
  public String greetingSubmit(@ModelAttribute Greeting greeting) {
    Greeting newPatient = patientds.createPatient(greeting);
    return "complete";
  }
}
