package org.javabasics.obbiettivi.serviceObbiettivi;

import java.util.HashMap;
import java.util.Map;

import org.javabasics.obbiettivi.modelObbiettivi.Obbiettivo;
import org.javabasics.obbiettivi.repositoryObbiettivi.RepositoryObbiettivi;

public class ServiceObbiettivi {

  // 1. Istanza statica privata e unica
  private static ServiceObbiettivi instance = null;

  public Map<Integer, Obbiettivo> obbiettiviMap = new HashMap<>();

  // 2. Costruttore privato
  private ServiceObbiettivi() {
  }

  // 3. Metodo pubblico statico per ottenere l’istanza unica
  public static ServiceObbiettivi getInstance() {
    if (instance == null) {
      instance = new ServiceObbiettivi();
    }
    return instance;
  }

  public void caricaObbiettivi() {
    obbiettiviMap = RepositoryObbiettivi.estraiDatiObbiettivo();
  }

  public void stampaObbiettivi() {
    for (Obbiettivo obbiettivo : obbiettiviMap.values()) {
      System.out.println("--------------------------------------------------------------------");
      System.out.println("");
      System.out.println(obbiettivo.toString());
      System.out.println("");
      System.out.println("--------------------------------------------------------------------");
    }
  }

  public Boolean obbiettivoLibero(Integer idObbiettivo) {
    Obbiettivo obbiettivo = ServiceObbiettivi.getInstance().obbiettiviMap.get(idObbiettivo);
    if (obbiettivo.isDisponibile()) {
      return true;
    } else {
      return false;
    }
  }

  public Obbiettivo estraiObbiettivo(Integer idObbiettivo) {
    return ServiceObbiettivi.getInstance().obbiettiviMap.get(idObbiettivo);
  }

  public void svuotaMappa() {
    ServiceObbiettivi.getInstance().obbiettiviMap.clear();
  }
}