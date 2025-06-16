package org.javabasics.utenti.repositoryUtente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.javabasics.utenti.modelUtente.Utente;

public class RepositoryUtente {
  public static ArrayList<Utente> estraiDatiUtente() {
    File fileUtenti = new File(
        "C:/Users/39328/Desktop/tutto/start_to_impact/8java/MeditActive/org/javabasics/dati/utenti.csv");
    ArrayList<Utente> utenti = new ArrayList<Utente>();

    if (fileUtenti.exists()) {
      try {
        BufferedReader br = new BufferedReader(new FileReader(fileUtenti));

        String linea;
        linea = br.readLine();

        while ((linea = br.readLine()) != null) {

          String[] datiUtente = linea.split(";");
          Integer id = Integer.parseInt(datiUtente[0]);
          Date dataNascita = new SimpleDateFormat("dd/MM/yyyy").parse(datiUtente[3]);
          Utente utente = new Utente(id, datiUtente[1], datiUtente[2], dataNascita, datiUtente[4], datiUtente[5]);

          utenti.add(utente);
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
    return utenti;
  }
}
