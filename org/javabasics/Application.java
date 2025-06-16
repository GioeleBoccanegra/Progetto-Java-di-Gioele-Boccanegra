package org.javabasics;

import java.util.HashMap;
import java.util.Map;

import org.javabasics.utenti.repositoryUtente.RepositoryUtente;
import org.javabasics.obbiettivi.controllerObbiettivi.ControllerObbiettivi;
import org.javabasics.obbiettivi.modelObbiettivi.Obbiettivo;
import org.javabasics.obbiettivi.repositoryObbiettivi.RepositoryObbiettivi;
import org.javabasics.prenotazioni.controllerPrenotazioni.ControllerPrenotazioni;
import org.javabasics.prenotazioni.modelPrenotazioni.Prenotazione;
import org.javabasics.prenotazioni.repositoryPrenotazioni.repositoryPrenotazioni;
import org.javabasics.utenti.controllerUtente.ControllerUtente;
import org.javabasics.utenti.modelUtente.Utente;

public class Application {

  public static void main(String[] args) {

    Map<Integer, Prenotazione> prenotazioniMap = new HashMap<>();
    Map<Integer, Obbiettivo> obbiettiviMap = new HashMap<>();
    Map<Integer, Utente> utentiMap = new HashMap<>();

    System.out.println("-------------------------------------------------------------------------");

    utentiMap = RepositoryUtente.estraiDatiUtente();
    ControllerUtente.stampaUtenti(utentiMap);

    System.out.println("-------------------------------------------------------------------------");

    obbiettiviMap = RepositoryObbiettivi.estraiDatiObbiettivos();
    ControllerObbiettivi.stampaObbiettivi(obbiettiviMap);
    System.out.println("-------------------------------------------------------------------------");

    prenotazioniMap = repositoryPrenotazioni.estraiDatiPrenotazioni();
    ControllerPrenotazioni.stampaPrenotazioni(prenotazioniMap);
    System.out.println("-------------------------------------------------------------------------");
  }
}