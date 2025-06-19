package org.javabasics.utenti.repositoryUtente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.javabasics.utenti.modelUtente.Utente;
import org.javabasics.utenti.serviceUtente.ServiceUtente;

public class RepositoryUtente {
  public static Map<Integer, Utente> estraiDatiUtente() {
    File fileUtenti = new File(
        "C:/Users/39328/Desktop/tutto/start_to_impact/8java/MeditActive/org/javabasics/dati/utenti.csv");

    ServiceUtente.getInstance().svuotaMappa();

    if (fileUtenti.exists()) {
      try (BufferedReader br = new BufferedReader(new FileReader(fileUtenti))) {
        String linea;
        linea = br.readLine(); // salta intestazione

        while ((linea = br.readLine()) != null) {
          String[] datiUtente = linea.split(";");
          Integer id = Integer.parseInt(datiUtente[0]);
          String nome = datiUtente[1];
          String cognome = datiUtente[2];
          Date dataNascita = new SimpleDateFormat("dd/MM/yyyy").parse(datiUtente[3]);
          String email = datiUtente[4];
          String telefono = datiUtente[5];

          Utente utente = new Utente(id, nome, cognome, dataNascita, email, telefono);
          ServiceUtente.getInstance().utentiMap.put(id, utente);
        }

      } catch (IOException | ParseException e) {
        System.out.println("Errore nella lettura del file: " + e.getMessage());
        e.printStackTrace();
      }

    } else {
      System.out.println("Il file utenti.csv non esiste");
    }

    return ServiceUtente.getInstance().utentiMap;
  }
}
