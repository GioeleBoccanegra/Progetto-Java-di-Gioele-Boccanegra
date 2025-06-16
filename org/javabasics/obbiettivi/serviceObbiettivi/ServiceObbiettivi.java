package org.javabasics.obbiettivi.serviceObbiettivi;

import java.util.HashMap;
import java.util.Map;

import org.javabasics.obbiettivi.modelObbiettivi.Obbiettivo;
import org.javabasics.obbiettivi.repositoryObbiettivi.RepositoryObbiettivi;

public class ServiceObbiettivi {
  protected Map<Integer, Obbiettivo> obbiettiviMap = new HashMap<>();

  public void caricaObbiettivi() {
    obbiettiviMap = RepositoryObbiettivi.estraiDatiObbiettivos();
  }

  public void stampaObbiettivi() {
    for (Obbiettivo obbiettivo : obbiettiviMap
        .values()) {
      System.out.println(obbiettivo.toString());
    }
  }
}
