package org.javabasics.prenotazioni.repositoryPrenotazioni;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.javabasics.prenotazioni.modelPrenotazioni.Prenotazione;

public class repositoryPrenotazioni {
  public static ArrayList<Prenotazione> estraiDatiPrenotazioni() {
    File filePrenotazioni = new File(
        "C:/Users/39328/Desktop/tutto/start_to_impact/8java/MeditActive/org/javabasics/dati/prenotazioni.csv");
    ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
    if (filePrenotazioni.exists()) {
      try {
        BufferedReader br = new BufferedReader(new FileReader(filePrenotazioni));

        String linea;
        linea = br.readLine();

        while ((linea = br.readLine()) != null) {

          String[] datiPrenotazione = linea.split(";");
          Integer id = Integer.parseInt(datiPrenotazione[0]);
          Integer idCorso = Integer.parseInt(datiPrenotazione[1]);
          Integer idUtente = Integer.parseInt(datiPrenotazione[2]);
          Date dataInizio = new SimpleDateFormat("dd/MM/yyyy").parse(datiPrenotazione[3]);
          Date dataFine = new SimpleDateFormat("dd/MM/yyyy").parse(datiPrenotazione[3]);

          Prenotazione prenotazione = new Prenotazione(id, idCorso, idUtente, dataInizio, dataFine);

          prenotazioni.add(prenotazione);
        }

        br.close();

      } catch (FileNotFoundException e) {
        System.out.println("errore nella lettura del file: " + e.getMessage());
      } catch (IOException e) {
        System.out.println("errore nella lettura del file: " + e.getMessage());
      } catch (ParseException e) {
        e.printStackTrace();
      }

    } else {
      System.out.println("il file non esiste");
    }
    return prenotazioni;
  }
}
