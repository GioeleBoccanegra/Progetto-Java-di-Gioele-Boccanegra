package org.javabasics.utenti.controllerUtente;

import org.javabasics.utenti.modelUtente.*;
import java.util.ArrayList;

public class ControllerUtente {

  public static void stampaUtenti(ArrayList<Utente> utenti) {
    for (Utente u : utenti) {
      System.out.println(u.toString());
    }
  }
}
