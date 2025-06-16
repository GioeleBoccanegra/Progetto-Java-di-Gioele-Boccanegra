package org.javabasics.mainController;

import java.util.Scanner;

public class mainController {
  public static void start() {
    System.out.println("Benvenuto in MEDITACTIVE");
    Scanner scan = new Scanner(System.in);
    int risposta = 0;

    do {

      menuInConsole();
      controllaRisposta(scan);
      risposta = scan.nextInt();

    } while (risposta != 0);

    scan.close();
  }

  public static void menuInConsole() {
    System.out.println("Digita 1: visualizare tutti gli obbiettivi nel sistema");
    System.out.println("Digita 2: decidere un obbiettivo esistente");
    System.out.println("Digita 3: rimuovere un obbiettivo");
    System.out.println("Digita 4: aggiungere nuovo utente");
    System.out.println("Digita 5: esporta file con obbiettivi disponibili");
    System.out.println("Digita 0: esci");
  }

  public static void controllaRisposta(Scanner scan) {
    while (!scan.hasNextInt()) {
      System.out.println("inserisci un numero valido tra le opzioni fornite");
      scan.next();
    }
  }
}
