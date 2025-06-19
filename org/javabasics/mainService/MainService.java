package org.javabasics.mainService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.javabasics.obbiettivi.modelObbiettivi.Obbiettivo;
import org.javabasics.obbiettivi.serviceObbiettivi.ServiceObbiettivi;
import org.javabasics.prenotazioni.modelPrenotazioni.Prenotazione;
import org.javabasics.prenotazioni.servicePrenotazioni.ServicePrenotazioni;
import org.javabasics.utenti.modelUtente.Utente;
import org.javabasics.utenti.repositoryUtente.RepositoryUtente;
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
        creaPrenotazione(scan);
        break;

      case 3:
        eliminaPrenotazione(scan);
        break;

      case 4:
        nuovoUtente(scan);

        break;

      case 5:

        RepositoryObbiettivi.creaFileObbiettivi();

        break;

    }

  }

  public void creaPrenotazione(Scanner scan) {
    System.out.println("Insercisci id obbiettivo");
    Integer idObbiettivo = scan.nextInt();
    if (ServiceObbiettivi.getInstance().obbiettiviMap.containsKey(idObbiettivo)) {
      System.out.println("Inserisci id utente");
      Integer idUtente = scan.nextInt();

      if (ServiceUtente.getInstance().utentiMap.containsKey(idUtente)) {

        if (ServiceObbiettivi.getInstance().obbiettivoLibero(idObbiettivo)) {
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

  }

  public void eliminaPrenotazione(Scanner scan) {
    System.out.println("Insercisci id prenotazione da eliminare");
    Integer idPrenotazione = scan.nextInt();

    if (ServicePrenotazioni.getInstance().prenotazioniMap.containsKey(idPrenotazione)) {

      Prenotazione p = ServicePrenotazioni.getInstance().prenotazioniMap.get(idPrenotazione);
      RepositoryPrenotazioni.eliminaPrenotazione(idPrenotazione);
      RepositoryObbiettivi.sostituisciDisponibilitaObbiettivo(p.getIdCorso());

      ServiceObbiettivi.getInstance().caricaObbiettivi();
      ServicePrenotazioni.getInstance().caricaPrenotazioni();

    } else {
      System.out.println("Prenotazione non trovata");
    }
  }

  public void nuovoUtente(Scanner scan) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Integer id = ServiceUtente.getProssimoIdLibero(ServiceUtente.getInstance().utentiMap);
    System.out.println("inserisci il nome dell' utente ");
    String nomeUtente = scan.next();
    System.out.println("inserisci il cognome dell' utente ");
    String cognomeUtente = scan.next();
    System.out.println("inserisci data di nascita nel formato gg/mm/aaaa");
    String inputData = scan.next();
    LocalDate dataNascita;
    if ((dataNascita = verificaData(inputData, formatter)) != null) {
      System.out.println("inserisci indirizzo utente");
      String indirizzoUtente = scan.next();
      System.out.println("inserisci id documento");
      String documentoId = scan.next();
      Utente nuovoUtente = new Utente(id, nomeUtente, cognomeUtente, dataNascita, indirizzoUtente, documentoId);
      RepositoryUtente.aggiungiUtente(nuovoUtente);
      ServiceUtente.getInstance().caricaUtenti();

    }

  }

  public LocalDate verificaData(String inputData, DateTimeFormatter formatter) {
    try {
      return LocalDate.parse(inputData, formatter);

    } catch (Exception e) {
      System.out.println("formato data non valida");
      return null;
    }
  }
}
