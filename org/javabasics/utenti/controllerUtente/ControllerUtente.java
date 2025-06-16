package org.javabasics.utenti.controllerUtente;

import org.javabasics.utenti.modelUtente.*;

import java.util.Map;

public class ControllerUtente {

  public static void stampaUtenti(Map<Integer, Utente> utenti) {
    for (Utente o : utenti.values()) {
      System.out.println(o.toString());
    }
  }
}
