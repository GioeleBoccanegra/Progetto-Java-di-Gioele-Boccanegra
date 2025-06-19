package org.javabasics.obbiettivi.repositoryObbiettivi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.javabasics.obbiettivi.modelObbiettivi.Obbiettivo;
import org.javabasics.obbiettivi.serviceObbiettivi.ServiceObbiettivi;

public class RepositoryObbiettivi {
  public static Map<Integer, Obbiettivo> estraiDatiObbiettivo() {
    File fileObbiettivi = new File(
        "C:/Users/39328/Desktop/tutto/start_to_impact/8java/MeditActive/org/javabasics/dati/obiettivi.csv");

    ServiceObbiettivi.getInstance().svuotaMappa();

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
          ServiceObbiettivi.getInstance().obbiettiviMap.put(id, obbiettivo);
        }

      } catch (IOException e) {
        System.out.println("Errore nella lettura del file: " + e.getMessage());
        e.printStackTrace();
      }

    } else {
      System.out.println("Il file obiettivi.csv non esiste");
    }

    return ServiceObbiettivi.getInstance().obbiettiviMap;
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

  public static void sostituisciDisponibilitaObbiettivo(Integer idObbiettivo) {
    File fileObbiettivi = new File(
        "C:/Users/39328/Desktop/tutto/start_to_impact/8java/MeditActive/org/javabasics/dati/obiettivi.csv");

    File fileTemp = new File(
        "C:/Users/39328/Desktop/tutto/start_to_impact/8java/MeditActive/org/javabasics/dati/obiettivi_temp.csv");

    try {
      BufferedReader br = new BufferedReader(new FileReader(fileObbiettivi));
      BufferedWriter bw = new BufferedWriter(new FileWriter(fileTemp));

      String linea;

      if ((linea = br.readLine()) != null) {
        bw.write(linea);
        bw.newLine();
      }

      while ((linea = br.readLine()) != null) {
        String[] dati = linea.split(";");
        int id = Integer.parseInt((dati[0]));
        if (id == idObbiettivo) {
          if ("SI".equals(dati[5])) {
            dati[5] = "NO";
          } else {
            dati[5] = "SI";
          }

          linea = String.join(";", dati);
        }

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

    if (!fileObbiettivi.delete()) {
      System.out.println("Impossibile eliminare il file originale.");
      return;
    }

    if (!fileTemp.renameTo(fileObbiettivi)) {
      System.out.println("Impossibile rinominare il file temporaneo.");
    }

  }

}
