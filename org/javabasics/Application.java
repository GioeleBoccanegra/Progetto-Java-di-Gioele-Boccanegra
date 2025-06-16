package org.javabasics;

import org.javabasics.mainController.MainController;
import org.javabasics.obbiettivi.serviceObbiettivi.ServiceObbiettivi;
import org.javabasics.prenotazioni.servicePrenotazioni.ServicePrenotazioni;
import org.javabasics.utenti.serviceUtente.ServiceUtente;;

public class Application {

  public static void main(String[] args) {

    ServiceUtente serviceUtente = ServiceUtente.getInstance();
    serviceUtente.caricaUtenti();

    ServiceObbiettivi serviceObbiettivi = ServiceObbiettivi.getInstance();
    serviceObbiettivi.caricaObbiettivi();

    ServicePrenotazioni servicePrenotazioni = ServicePrenotazioni.getInstance();
    servicePrenotazioni.caricaPrenotazioni();
    MainController.start();
  }
}