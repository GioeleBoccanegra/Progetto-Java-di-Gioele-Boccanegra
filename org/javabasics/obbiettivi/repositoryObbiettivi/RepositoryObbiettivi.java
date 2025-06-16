package org.javabasics.obbiettivi.repositoryObbiettivi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.javabasics.obbiettivi.modelObbiettivi.Obbiettivo;

public class RepositoryObbiettivi {
  public static ArrayList<Obbiettivo> estraiDatiObbiettivos() {
    File fileObbiettivi = new File(
        "C:/Users/39328/Desktop/tutto/start_to_impact/8java/MeditActive/org/javabasics/dati/obiettivi.csv");
    ArrayList<Obbiettivo> obbiettivi = new ArrayList<Obbiettivo>();
    if (fileObbiettivi.exists()) {
      try {
        BufferedReader br = new BufferedReader(new FileReader(fileObbiettivi));

        String linea;
        linea = br.readLine();

        while ((linea = br.readLine()) != null) {

          String[] datiObbiettivo = linea.split(";");
          Integer id = Integer.parseInt(datiObbiettivo[0]);
          Integer durata = Integer.parseInt(datiObbiettivo[4]);

          Obbiettivo obbiettivo = new Obbiettivo(id, datiObbiettivo[1], datiObbiettivo[2], datiObbiettivo[3], durata,
              datiObbiettivo[5]);

          obbiettivi.add(obbiettivo);
        }

        br.close();

      } catch (FileNotFoundException e) {
        System.out.println("errore nella lettura del file: " + e.getMessage());
      } catch (IOException e) {
        System.out.println("errore nella lettura del file: " + e.getMessage());
      }

    } else {
      System.out.println("il file non esiste");
    }
    return obbiettivi;
  }
}