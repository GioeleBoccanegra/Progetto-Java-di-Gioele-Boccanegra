package org.javabasics.utenti.repositoryUtente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.javabasics.utenti.modelUtente.Utente;
import org.javabasics.utenti.serviceUtente.ServiceUtente;

public class RepositoryUtente {

  public static final Path FILE_PATH = Paths.get("org", "javabasics", "dati", "utenti.csv");

  public static Map<Integer, Utente> estraiDatiUtente() {
    File fileUtenti = FILE_PATH.toFile();

    ServiceUtente.getInstance().svuotaMappa();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    controllaEsistenzaFile(fileUtenti);
    try (BufferedReader br = new BufferedReader(new FileReader(fileUtenti))) {
      String linea;
      linea = br.readLine(); // salta intestazione

      while ((linea = br.readLine()) != null) {
        String[] datiUtente = linea.split(";");

        if (linea.trim().isEmpty()) {
          continue;
        }

        if (datiUtente.length < 6) {
          System.out.println("Riga incompleta (ignorata): " + linea);
          continue;
        }

        if (datiUtente[0].trim().isEmpty()) {
          throw new RuntimeException("Campo id vuoto :" + linea);
        }

        Integer id = Integer.parseInt(datiUtente[0]);
        String nome = datiUtente[1];
        String cognome = datiUtente[2];
        LocalDate dataNascita = LocalDate.parse(datiUtente[3], formatter);
        String email = datiUtente[4];
        String telefono = datiUtente[5];

        Utente utente = new Utente(id, nome, cognome, dataNascita, email, telefono);
        ServiceUtente.getInstance().utentiMap.put(id, utente);
      }

    } catch (IOException e) {
      System.out.println("Errore nella lettura del file: " + e.getMessage());
      e.printStackTrace();
    }

    return ServiceUtente.getInstance().utentiMap;
  }

  public static void aggiungiUtente(Utente u) {
    File file = FILE_PATH.toFile();

    controllaEsistenzaFile(file);
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
      String linea;

      linea = u.formatta();
      bw.newLine();
      bw.write(linea);
      System.out.println("utente creato :" + u);
      bw.close();
    } catch (IOException e) {
      System.out.println("Errore nella scrittura del file: " + e.getMessage());
    }

  }

  public static void controllaEsistenzaFile(File file) {
    if (!file.exists()) {
      throw new RuntimeException("file non trovato");
    }
  }
}
