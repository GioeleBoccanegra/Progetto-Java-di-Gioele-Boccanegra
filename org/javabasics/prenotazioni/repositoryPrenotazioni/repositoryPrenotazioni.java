package org.javabasics.prenotazioni.repositoryPrenotazioni;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.javabasics.prenotazioni.modelPrenotazioni.Prenotazione;

public class RepositoryPrenotazioni {

  public static Map<Integer, Prenotazione> estraiDatiPrenotazioni() {
    File filePrenotazioni = new File(
        "C:/Users/39328/Desktop/tutto/start_to_impact/8java/MeditActive/org/javabasics/dati/prenotazioni.csv");

    Map<Integer, Prenotazione> prenotazioniMap = new HashMap<>();

    if (filePrenotazioni.exists()) {
      try (BufferedReader br = new BufferedReader(new FileReader(filePrenotazioni))) {

        String linea;
        linea = br.readLine(); // salta intestazione

        while ((linea = br.readLine()) != null) {
          String[] datiPrenotazione = linea.split(";");

          Integer id = Integer.parseInt(datiPrenotazione[0]);
          Integer idCorso = Integer.parseInt(datiPrenotazione[1]);
          Integer idUtente = Integer.parseInt(datiPrenotazione[2]);
          Date dataInizio = new SimpleDateFormat("dd/MM/yyyy").parse(datiPrenotazione[3]);
          Date dataFine = new SimpleDateFormat("dd/MM/yyyy").parse(datiPrenotazione[4]);

          Prenotazione prenotazione = new Prenotazione(id, idCorso, idUtente, dataInizio, dataFine);

          prenotazioniMap.put(id, prenotazione);
        }

      } catch (IOException | ParseException e) {
        System.out.println("Errore nella lettura del file: " + e.getMessage());
        e.printStackTrace();
      }

    } else {
      System.out.println("Il file prenotazioni.csv non esiste");
    }

    return prenotazioniMap;
  }

  
}
