package org.javabasics;

import org.javabasics.mainController.mainController;
import org.javabasics.obbiettivi.serviceObbiettivi.ServiceObbiettivi;
import org.javabasics.prenotazioni.servicePrenotazioni.ServicePrenotazioni;
import org.javabasics.utenti.serviceUtente.ServiceUtente;;

public class Application {

  public static void main(String[] args) {

    ServiceUtente serviceUtente = new ServiceUtente();
    serviceUtente.caricaUtenti();

    ServiceObbiettivi serviceObbiettivi = new ServiceObbiettivi();
    serviceObbiettivi.caricaObbiettivi();

    ServicePrenotazioni servicePrenotazioni = new ServicePrenotazioni();
    servicePrenotazioni.caricaPrenotazioni();

    serviceUtente.stampaUtenti();
    serviceObbiettivi.stampaObbiettivi();
    servicePrenotazioni.stampaPrenotazioni();

    mainController.start();
  }
}