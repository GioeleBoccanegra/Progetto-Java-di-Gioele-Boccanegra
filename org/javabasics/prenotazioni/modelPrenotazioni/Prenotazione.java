package org.javabasics.prenotazioni.modelPrenotazioni;

import java.util.Date;

public class Prenotazione {
  protected Integer id;
  protected Integer idCorso;
  protected Integer idUtente;
  protected Date dataInizio;
  protected Date dataFine;

  public Prenotazione(Integer id, Integer idCorso, Integer idUtente, Date dataInizio, Date dataFine) {
    this.id = id;
    this.idCorso = idCorso;
    this.idUtente = idUtente;
    this.dataInizio = dataInizio;
    this.dataFine = dataFine;

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
