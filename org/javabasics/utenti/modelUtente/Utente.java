package org.javabasics.utenti.modelUtente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utente {
  protected Integer id;
  protected String nome;
  protected String cognome;
  protected LocalDate dataNascita;
  protected String indirizzo;
  protected String documentoId;

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public Utente(Integer id, String nome, String cognome, LocalDate dataNascita, String indirizzo, String documentoId) {
    this.id = id;
    this.nome = nome;
    this.cognome = cognome;
    this.dataNascita = dataNascita;
    this.indirizzo = indirizzo;
    this.documentoId = documentoId;

  }

  public Integer getId() {
    return id;
  }

  public String formatta() {
    return id + ";"
        + nome + ";"
        + cognome + ";"
        + (dataNascita != null ? dataNascita.format(FORMATTER) : "") + ";"
        + indirizzo + ";"
        + documentoId;
  }

  @Override
  public String toString() {
    return "Utente{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", cognome='" + cognome + '\'' +
        ", dataNascita=" + dataNascita +
        ", indirizzo='" + indirizzo + '\'' +
        ", documentoId='" + documentoId + '\'' +
        '}';
  }
}
