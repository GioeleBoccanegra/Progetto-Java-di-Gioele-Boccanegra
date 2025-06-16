package org.javabasics.prenotazioni.servicePrenotazioni;

import java.util.HashMap;
import java.util.Map;

import org.javabasics.prenotazioni.modelPrenotazioni.Prenotazione;
import org.javabasics.prenotazioni.repositoryPrenotazioni.RepositoryPrenotazioni;

public class ServicePrenotazioni {
  protected Map<Integer, Prenotazione> prenotazioniMap = new HashMap<>();

  public void caricaPrenotazioni() {
    prenotazioniMap = RepositoryPrenotazioni.estraiDatiPrenotazioni();
  }

  public void stampaPrenotazioni() {
    for (Prenotazione prenotazione : prenotazioniMap.values()) {
      System.out.println(prenotazione.toString());
    }

  }
}
