package org.javabasics.utenti.serviceUtente;

import java.util.HashMap;
import java.util.Map;

import org.javabasics.utenti.modelUtente.Utente;
import org.javabasics.utenti.repositoryUtente.RepositoryUtente;

public class ServiceUtente {
  private static ServiceUtente instance = null;

  private ServiceUtente() {

  }

  public Map<Integer, Utente> utentiMap = new HashMap<>();

  public static ServiceUtente getInstance() {

    if (instance == null) {
      instance = new ServiceUtente();
    }

    return instance;
  }

  public void caricaUtenti() {
    utentiMap = RepositoryUtente.estraiDatiUtente();
  }

  public void stampaUtenti() {
    for (Utente utente : utentiMap.values()) {
      System.out.println(utente.toString());
    }
  }

  public static int getProssimoIdLibero(Map<Integer, Utente> mappa) {
    if (mappa.isEmpty()) {
      return 1;
    }
    return mappa.keySet().stream().max(Integer::compareTo).get() + 1;
  }

  public void svuotaMappa() {
    ServiceUtente.getInstance().utentiMap.clear();
  }

}
