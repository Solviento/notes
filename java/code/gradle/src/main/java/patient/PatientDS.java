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
import java.util.Random;

// Data structure class for all patients being entered into system
public class PatientDS{

  private static final Map<Long, Greeting> PATIENT_MAP = new HashMap<>();

  // generates unique ID if the system admin needs it
  private Long generateID(){
    Random ran = new Random();
    int n = ran.nextInt(2000) + ran.nextInt(2000);
    return Long.valueOf(n);
  }

  // retrieve list of all patients so far
  public List<Greeting> getPatients(){
    List<Greeting> list = new ArrayList<>();
    list.addAll(PATIENT_MAP.values());
    return list;
  }

  // should rename to addPatient
  // adds patient to list
  public Greeting createPatient(Greeting g){
    Long id = this.generateID();
    PATIENT_MAP.put(id, g);
    return g;
  }
}
