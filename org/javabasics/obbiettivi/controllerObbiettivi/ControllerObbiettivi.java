package org.javabasics.obbiettivi.controllerObbiettivi;

import java.util.ArrayList;

import org.javabasics.obbiettivi.modelObbiettivi.Obbiettivo;

public class ControllerObbiettivi {
  public static void stampaObbiettivi(ArrayList<Obbiettivo> obbiettivi) {
    for (Obbiettivo u : obbiettivi) {
      System.out.println(u.toString());
    }
  }
}
