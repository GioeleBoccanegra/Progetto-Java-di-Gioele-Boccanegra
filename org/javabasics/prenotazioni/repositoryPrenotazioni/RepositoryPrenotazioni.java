package org.javabasics.prenotazioni.repositoryPrenotazioni;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Map;

import org.javabasics.prenotazioni.modelPrenotazioni.Prenotazione;
import org.javabasics.prenotazioni.servicePrenotazioni.ServicePrenotazioni;

public class RepositoryPrenotazioni {

  public static Map<Integer, Prenotazione> estraiDatiPrenotazioni() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    File filePrenotazioni = new File(
        "C:/Users/39328/Desktop/tutto/start_to_impact/8java/MeditActive/org/javabasics/dati/prenotazioni.csv");

    ServicePrenotazioni.getInstance().svuotaMappa();

    if (filePrenotazioni.exists()) {

      try (BufferedReader br = new BufferedReader(new FileReader(filePrenotazioni))) {

        String linea;
        linea = br.readLine(); // salta intestazione

        while ((linea = br.readLine()) != null) {
          String[] datiPrenotazione = linea.split(";");

          if (linea.trim().isEmpty()) {
            continue;
          }

          if (datiPrenotazione.length < 5) {
            System.out.println("Riga incompleta (ignorata): " + linea);
            continue;
          }

          if (datiPrenotazione[0].trim().isEmpty()) {
            throw new RuntimeException("Campo id vuoto :" + linea);
          }

          if (datiPrenotazione[1].trim().isEmpty()) {
            throw new RuntimeException("Campo id Corso vuoto :" + linea);
          }

          if (datiPrenotazione[2].trim().isEmpty()) {
            throw new RuntimeException("Campo id Obbieyttivo vuoto :" + linea);
          }

          Integer id = Integer.parseInt(datiPrenotazione[0]);
          Integer idCorso = Integer.parseInt(datiPrenotazione[1]);
          Integer idUtente = Integer.parseInt(datiPrenotazione[2]);
          LocalDate dataInizio = LocalDate.parse(datiPrenotazione[3], formatter);
          LocalDate dataFine = LocalDate.parse(datiPrenotazione[4], formatter);

          Prenotazione prenotazione = new Prenotazione(id, idCorso, idUtente, dataInizio, dataFine);

          ServicePrenotazioni.getInstance().prenotazioniMap.put(id, prenotazione);
        }

      } catch (IOException e) {
        System.out.println("Errore nella lettura del file: " + e.getMessage());
        e.printStackTrace();
      }

    } else {
      System.out.println("Il file prenotazioni.csv non esiste");
    }

    return ServicePrenotazioni.getInstance().prenotazioniMap;
  }

  public static void aggiungiPrenotazione(Prenotazione p) {

    File fileP = new File(
        "C:/Users/39328/Desktop/tutto/start_to_impact/8java/MeditActive/org/javabasics/dati/prenotazioni.csv");
    try {
      if (fileP.exists()) {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileP, true));
        bw.newLine();
        bw.write(p.formatta());
        bw.close();
        System.out.println("prenotazione numero " + p.getId() + " aggiunta");
      } else {
        System.out.println("file non trovato");
      }
    } catch (IOException e) {
      System.out.println("Errore nella scrittura del file: " + e.getMessage());
    }

  }

  public static void eliminaPrenotazione(Integer idPrenotazione) {
    File file = new File(
        "C:/Users/39328/Desktop/tutto/start_to_impact/8java/MeditActive/org/javabasics/dati/prenotazioni.csv");

    File fileTemp = new File(
        "C:/Users/39328/Desktop/tutto/start_to_impact/8java/MeditActive/org/javabasics/dati/prenotazioni_temp.csv");

    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      BufferedWriter bw = new BufferedWriter(new FileWriter(fileTemp));

      String linea;

      if ((linea = br.readLine()) != null) {
        bw.write(linea);
        bw.newLine();
      }

      while ((linea = br.readLine()) != null) {
        String[] dati = linea.split(";");
        int id = Integer.parseInt((dati[0]));
        if (id == idPrenotazione) {

          continue;
        }

        linea = String.join(";", dati);
        bw.write(linea);
        bw.newLine();
      }

      bw.close();
      br.close();

    } catch (IOException e) {
      System.out.println("Errore durante l'aggiornamento del file: " + e.getMessage());
      e.printStackTrace();
      return;
    }

    if (!file.delete()) {
      System.out.println("Impossibile eliminare il file originale.");
      return;
    }

    if (!fileTemp.renameTo(file)) {
      System.out.println("Impossibile rinominare il file temporaneo.");
    }

    System.out.println("prenotazione numero " + idPrenotazione + " eliminata");

  }

}
