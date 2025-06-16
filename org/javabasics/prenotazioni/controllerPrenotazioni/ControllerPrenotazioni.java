package org.javabasics.prenotazioni.controllerPrenotazioni;

import java.util.Map;

import org.javabasics.prenotazioni.modelPrenotazioni.Prenotazione;

public class ControllerPrenotazioni {
  public static void stampaPrenotazioni(Map<Integer, Prenotazione> prenotazioni) {
    for (Prenotazione o : prenotazioni.values()) {
      System.out.println(o.toString());
    }
  }
}
