package org.javabasics.mainService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import org.javabasics.obbiettivi.modelObbiettivi.Obbiettivo;
import org.javabasics.obbiettivi.serviceObbiettivi.ServiceObbiettivi;
import org.javabasics.prenotazioni.modelPrenotazioni.Prenotazione;
import org.javabasics.prenotazioni.servicePrenotazioni.ServicePrenotazioni;
import org.javabasics.utenti.modelUtente.Utente;
import org.javabasics.utenti.serviceUtente.ServiceUtente;
import org.javabasics.obbiettivi.repositoryObbiettivi.RepositoryObbiettivi;
import org.javabasics.prenotazioni.repositoryPrenotazioni.RepositoryPrenotazioni;

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

              LocalDate dataInizio = LocalDate.now();

              LocalDate dataFine = dataInizio.plusDays(7);
              Prenotazione prenotazione = new Prenotazione(nuovoId, idObbiettivo, idUtente, dataInizio, dataFine);
              RepositoryPrenotazioni.aggiungiPrenotazione(prenotazione);
              RepositoryObbiettivi.sostituisciDisponibilitaObbiettivo(idObbiettivo);
              ServiceObbiettivi.getInstance().caricaObbiettivi();
              ServicePrenotazioni.getInstance().caricaPrenotazioni();
              System.out.println(ServiceObbiettivi.getInstance().obbiettiviMap);
              System.out.println(ServicePrenotazioni.getInstance().prenotazioniMap);

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

      case 3:
        System.out.println("Insercisci id obbiettivo da eliminare");
        Integer idObb = scan.nextInt();
        if (ServiceObbiettivi.getInstance().obbiettiviMap.containsKey(idObb)) {
          RepositoryObbiettivi.RimuoviObbiettivofile(idObb);
          serviceObbiettivi.caricaObbiettivi();
          System.out.println("prenotazione numero " + idObb + " eliminata");

        } else {
          System.out.println("Prenotazione non trovata");
        }
        break;

      case 4:
        System.out.println(ServiceUtente.getInstance().utentiMap);
        Integer id = ServiceUtente.getProssimoIdLibero(ServiceUtente.getInstance().utentiMap);
        System.out.println("inserisci il nome dell' utente ");
        String nomeUtente = scan.next();
        System.out.println("inserisci il cognome dell' utente ");
        String cognomeUtente = scan.next();
        System.out.println("inserisci data di nascita nel formato gg/mm/aaaa");
        String inputData = scan.next();
        Date dataNascita;
        if ((dataNascita = verificaData(inputData)) != null) {
          System.out.println("inserisci indirizzo utente");
          String indirizzoUtente = scan.next();
          System.out.println("inserisci id documento");
          String documentoId = scan.next();
          Utente nuovoUtente = new Utente(id, nomeUtente, cognomeUtente, dataNascita, indirizzoUtente, documentoId);
          ServiceUtente.getInstance().utentiMap.put(id, nuovoUtente);
          System.out.println("utente creato " + nuovoUtente);
          System.out.println(ServiceUtente.getInstance().utentiMap);

        }

        break;

      case 5:

        System.out.println("inizio esportazione file");

        RepositoryObbiettivi.creaFileObbiettivi();

        System.out.println("fine esportazione file");

        break;

    }

  }

  public Date verificaData(String inputData) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      sdf.setLenient(false);
      Date dataNascita = sdf.parse(inputData);
      return dataNascita;
    } catch (Exception e) {
      System.out.println("formato data non valida");
      return null;
    }
  }

}
