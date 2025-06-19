package org.javabasics.prenotazioni.servicePrenotazioni;

import java.util.HashMap;
import java.util.Map;

import org.javabasics.prenotazioni.modelPrenotazioni.Prenotazione;
import org.javabasics.prenotazioni.repositoryPrenotazioni.RepositoryPrenotazioni;

public class ServicePrenotazioni {

  private static ServicePrenotazioni instance = null;

  public Map<Integer, Prenotazione> prenotazioniMap = new HashMap<>();

  private ServicePrenotazioni() {

  }

  public static ServicePrenotazioni getInstance() {
    if (instance == null) {
      instance = new ServicePrenotazioni();
    }
    return instance;
  }

  public void caricaPrenotazioni() {
    prenotazioniMap = RepositoryPrenotazioni.estraiDatiPrenotazioni();
  }

  public void stampaPrenotazioni() {
    for (Prenotazione prenotazione : prenotazioniMap.values()) {
      System.out.println(prenotazione.toString());
    }

  }

  public static int getProssimoIdLibero(Map<Integer, Prenotazione> mappa) {
    if (mappa.isEmpty()) {
      return 1;
    }
    return mappa.keySet().stream().max(Integer::compareTo).get() + 1;
  }

  public void svuotaMappa() {
    ServicePrenotazioni.getInstance().prenotazioniMap.clear();
  }

}
