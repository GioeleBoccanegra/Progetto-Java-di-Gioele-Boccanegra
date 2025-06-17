package org.javabasics.mainService;

import java.util.Date;
import java.util.Scanner;

import org.javabasics.obbiettivi.modelObbiettivi.Obbiettivo;
import org.javabasics.obbiettivi.serviceObbiettivi.ServiceObbiettivi;
import org.javabasics.prenotazioni.modelPrenotazioni.Prenotazione;
import org.javabasics.prenotazioni.servicePrenotazioni.ServicePrenotazioni;
import org.javabasics.utenti.serviceUtente.ServiceUtente;

public class MainService {
  public void ScegliOperazione(Integer risposta, Scanner scan) {
    ServiceObbiettivi serviceObbiettivi = ServiceObbiettivi.getInstance();

    switch (risposta) {
      case 1:

        serviceObbiettivi.stampaObbiettivi();

        break;
      case 2:
        System.out.println("Insercisci id obbiettivo");
        Integer idObbiettivo = scan.nextInt();
        if (ServiceObbiettivi.getInstance().obbiettiviMap.containsKey(idObbiettivo)) {
          System.out.println("Inserisci id utente");
          Integer idUtente = scan.nextInt();

          if (ServiceUtente.getInstance().utentiMap.containsKey(idUtente)) {

            if (serviceObbiettivi.obbiettivoLibero(idObbiettivo)) {
              Obbiettivo o = ServiceObbiettivi.getInstance().estraiObbiettivo(idObbiettivo);
              o.modificaDisponibilitaObbiettivo();
              int nuovoId = ServicePrenotazioni.getProssimoIdLibero(ServicePrenotazioni.getInstance().prenotazioniMap);
              Date dataInizio = new Date();
              Date dataFine = null;
              Prenotazione prenotazione = new Prenotazione(nuovoId, idObbiettivo, idUtente, dataInizio, dataFine);
              ServicePrenotazioni.getInstance().prenotazioniMap.put(nuovoId, prenotazione);

              System.out.println("Prenotazione creata: " + prenotazione);

            } else {
              System.out.println("obbiettivo già prenotato");
            }

          } else {
            System.out.println("Utente non trovato");
          }
        } else {
          System.out.println("Obbiettivo non trovato");
        }

        break;

    }

  }

}
