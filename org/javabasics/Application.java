package org.javabasics;

import java.util.ArrayList;

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

    ArrayList<Utente> utenti = new ArrayList<Utente>();
    ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
    ArrayList<Obbiettivo> obbiettivi = new ArrayList<Obbiettivo>();

    System.out.println("-------------------------------------------------------------------------");

    utenti = RepositoryUtente.estraiDatiUtente();
    ControllerUtente.stampaUtenti(utenti);

    System.out.println("-------------------------------------------------------------------------");

    obbiettivi = RepositoryObbiettivi.estraiDatiObbiettivos();
    ControllerObbiettivi.stampaObbiettivi(obbiettivi);
    System.out.println("-------------------------------------------------------------------------");

    prenotazioni = repositoryPrenotazioni.estraiDatiPrenotazioni();
    ControllerPrenotazioni.stampaPrenotazioni(prenotazioni);
    System.out.println("-------------------------------------------------------------------------");
  }
}