package org.javabasics.prenotazioni.modelPrenotazioni;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prenotazione {
  protected Integer id;
  protected Integer idCorso;
  protected Integer idUtente;
  protected LocalDate dataInizio;
  protected LocalDate dataFine;

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public Prenotazione(Integer id, Integer idCorso, Integer idUtente, LocalDate dataInizio, LocalDate dataFine) {
    this.id = id;
    this.idCorso = idCorso;
    this.idUtente = idUtente;
    this.dataInizio = dataInizio;
    this.dataFine = dataFine;

  }

  public Integer getIdCorso() {
    return idCorso;
  }

  public String formatta() {
    return id + ";" + idCorso + ";" + idUtente + ";" +
        (dataInizio != null ? dataInizio.format(FORMATTER) : "") + ";" +
        (dataFine != null ? dataFine.format(FORMATTER) : "");
  }

  @Override
  public String toString() {
    return "Prenotazione{" +
        "id=" + id +
        ", idCorso=" + idCorso +
        ", idUtente=" + idUtente +
        ", dataInizio=" + dataInizio +
        ", dataFine=" + dataFine +
        '}';
  }

}
