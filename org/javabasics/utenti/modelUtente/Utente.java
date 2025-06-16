package org.javabasics.utenti.modelUtente;

import java.util.Date;

public class Utente {
  protected Integer id;
  protected String nome;
  protected String cognome;
  protected Date dataNascita;
  protected String indirizzo;
  protected String documentoId;

  public Utente(Integer id, String nome, String cognome, Date dataNascita, String indirizzo, String documentoId) {
    this.id = id;
    this.nome = nome;
    this.cognome = cognome;
    this.dataNascita = dataNascita;
    this.indirizzo = indirizzo;
    this.documentoId = documentoId;

  }

  public String getName() {
    return this.nome;
  }

  public String getCognome() {
    return this.cognome;
  }

  public Date getDataNascita() {
    return this.dataNascita;
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
