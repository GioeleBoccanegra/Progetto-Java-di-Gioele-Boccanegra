package org.javabasics.prenotazioni.controllerPrenotazioni;

import java.util.ArrayList;

import org.javabasics.prenotazioni.modelPrenotazioni.Prenotazione;

public class ControllerPrenotazioni {
  public static void stampaPrenotazioni(ArrayList<Prenotazione> prenotazioni) {
    for (Prenotazione u : prenotazioni) {
      System.out.println(u.toString());
    }
  }
}
