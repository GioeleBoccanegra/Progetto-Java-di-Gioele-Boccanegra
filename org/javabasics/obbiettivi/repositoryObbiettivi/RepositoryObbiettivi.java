package org.javabasics.obbiettivi.repositoryObbiettivi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.javabasics.obbiettivi.modelObbiettivi.Obbiettivo;
import org.javabasics.obbiettivi.serviceObbiettivi.ServiceObbiettivi;

public class RepositoryObbiettivi {
  public static Map<Integer, Obbiettivo> estraiDatiObbiettivos() {
    File fileObbiettivi = new File(
        "C:/Users/39328/Desktop/tutto/start_to_impact/8java/MeditActive/org/javabasics/dati/obiettivi.csv");

    Map<Integer, Obbiettivo> obbiettiviMap = new HashMap<>();

    if (fileObbiettivi.exists()) {
      try (BufferedReader br = new BufferedReader(new FileReader(fileObbiettivi))) {
        String linea;
        linea = br.readLine(); // salta intestazione

        while ((linea = br.readLine()) != null) {
          String[] datiObbiettivo = linea.split(";");
          Integer id = Integer.parseInt(datiObbiettivo[0]);
          String nome = datiObbiettivo[1];
          String descrizione = datiObbiettivo[2];
          String tipologia = datiObbiettivo[3];
          Integer durata = Integer.parseInt(datiObbiettivo[4]);
          Boolean disponibile = null;
          if ("SI".equals(datiObbiettivo[5])) {
            disponibile = true;
          } else {
            disponibile = false;
          }

          Obbiettivo obbiettivo = new Obbiettivo(id, nome, descrizione, tipologia, durata, disponibile);
          obbiettiviMap.put(id, obbiettivo);
        }

      } catch (IOException e) {
        System.out.println("Errore nella lettura del file: " + e.getMessage());
        e.printStackTrace();
      }

    } else {
      System.out.println("Il file obiettivi.csv non esiste");
    }

    return obbiettiviMap;
  }

  public static void creaFileObbiettivi() {

    Date dora = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
    String dataFormattata = sdf.format(dora);
    File fileObbiettivi = new File(
        "C:/Users/39328/Desktop/tutto/start_to_impact/8java/MeditActive/org/javabasics/dati/obbiettivi"
            + dataFormattata + ".csv");

    try {
      fileObbiettivi.createNewFile();
      if (fileObbiettivi.exists()) {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileObbiettivi, true));

        bw.write("id;nome;descrizione;tipologia;durata;disponibile");
        bw.newLine();

        for (Obbiettivo o : ServiceObbiettivi.getInstance().obbiettiviMap.values()) {
          if (o.isDisponibile()) {
            bw.write(o.formata());
            bw.newLine();
          }

        }
        bw.close();
      } else {
        System.out.println("file non trovato");
      }
    } catch (IOException e) {
      System.out.println("errore nella creazione del file : " + e.getMessage());
    }

  }
}
