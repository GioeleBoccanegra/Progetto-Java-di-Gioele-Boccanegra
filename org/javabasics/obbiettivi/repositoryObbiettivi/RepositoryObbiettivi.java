package org.javabasics.obbiettivi.repositoryObbiettivi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.javabasics.obbiettivi.modelObbiettivi.Obbiettivo;
import org.javabasics.obbiettivi.serviceObbiettivi.ServiceObbiettivi;

public class RepositoryObbiettivi {

  public static final Path FILE_PATH = Paths.get("org", "javabasics", "dati", "obiettivi.csv");

  public static Map<Integer, Obbiettivo> estraiDatiObbiettivo() {
    File fileObbiettivi = FILE_PATH.toFile();

    ServiceObbiettivi.getInstance().svuotaMappa();

    controllaEsistenzaFile(fileObbiettivi);
    try (BufferedReader br = new BufferedReader(new FileReader(fileObbiettivi))) {

      String linea;
      linea = br.readLine(); // salta intestazione

      while ((linea = br.readLine()) != null) {

        String[] datiObbiettivo = linea.split(";");

        if (linea.trim().isEmpty()) {
          continue;
        }

        if (datiObbiettivo.length < 6) {
          System.out.println("Riga incompleta (ignorata): " + linea);
          continue;
        }

        if (datiObbiettivo[0].trim().isEmpty()) {
          throw new RuntimeException("Campo id vuoto :" + linea);
        }

        if (datiObbiettivo[4].trim().isEmpty()) {
          throw new RuntimeException("Campo duarata :" + linea);
        }

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

    return ServiceObbiettivi.getInstance().obbiettiviMap;
  }

  public static void creaFileObbiettivi() {

    Date dora = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
    String dataFormattata = sdf.format(dora);
    File fileObbiettivi = new File(
        "org/javabasics/dati/obbiettivi"
            + dataFormattata + ".csv");

    try {
      fileObbiettivi.createNewFile();

      controllaEsistenzaFile(fileObbiettivi);

      try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileObbiettivi, true))) {

        bw.write("id;nome;descrizione;tipologia;durata;disponibile");
        bw.newLine();

        for (Obbiettivo o : ServiceObbiettivi.getInstance().obbiettiviMap.values()) {
          if (o.isDisponibile()) {
            bw.write(o.formatta());
            bw.newLine();
          }
        }

        System.out.println(
            "file creato con successo: org/javabasics/dati/obbiettivi"
                + dataFormattata + ".csv");

      }

    } catch (IOException e) {
      System.out.println("errore nella creazione del file : " + e.getMessage());
    }
  }

  public static void sostituisciDisponibilitaObbiettivo(Integer idObbiettivo) {
    File fileObbiettivi = FILE_PATH.toFile();
    controllaEsistenzaFile(fileObbiettivi);

    Path FILE_TEMP_PATH = Paths.get("org", "javabasics", "dati", "obiettivi_temp.csv");

    File fileTemp = FILE_TEMP_PATH.toFile();

    try (BufferedReader br = new BufferedReader(new FileReader(fileObbiettivi));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileTemp))) {

      String linea;

      if ((linea = br.readLine()) != null) {
        bw.write(linea);
        bw.newLine();
      }

      while ((linea = br.readLine()) != null) {
        String[] datiObbiettivo = linea.split(";");

        if (linea.trim().isEmpty()) {
          continue;
        }

        if (datiObbiettivo.length < 6) {
          System.out.println("Riga incompleta (ignorata): " + linea);
          continue;
        }

        if (datiObbiettivo[0].trim().isEmpty()) {
          throw new RuntimeException("Campo id vuoto :" + linea);
        }

        if (datiObbiettivo[4].trim().isEmpty()) {
          throw new RuntimeException("Campo duarata :" + linea);
        }

        int id = Integer.parseInt((datiObbiettivo[0]));
        if (id == idObbiettivo) {
          if ("SI".equals(datiObbiettivo[5])) {
            datiObbiettivo[5] = "NO";
            System.out.println("cambiata dispnibilità obbiettivo: " + idObbiettivo);
          } else {
            datiObbiettivo[5] = "SI";
            System.out.println("cambiata dispnibilità obbiettivo: " + idObbiettivo);
          }

          linea = String.join(";", datiObbiettivo);
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

  public static void controllaEsistenzaFile(File file) {
    if (!file.exists()) {
      throw new RuntimeException("file non trovato");
    }
  }

}
