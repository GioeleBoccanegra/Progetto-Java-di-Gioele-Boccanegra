package org.javabasics.obbiettivi.modelObbiettivi;

public class Obbiettivo {
  // Source code is decompiled from a .class file using FernFlower decompiler.

  protected Integer id;
  protected String nome;
  protected String descrizione;
  protected String tipologia;
  protected Integer durata;
  protected Boolean disponibile;

  public Obbiettivo(Integer id, String nome, String descrizione, String tipologia, Integer durata,
      Boolean disponibile) {
    this.id = id;
    this.nome = nome;
    this.descrizione = descrizione;
    this.tipologia = tipologia;
    this.durata = durata;
    this.disponibile = disponibile;
  }

  public Boolean isDisponibile() {

    if (this.disponibile) {
      return true;
    } else {
      return false;
    }
  }

  public void modificaDisponibilitaObbiettivo() {
    if (this.disponibile)
      this.disponibile = false;
    else {
      this.disponibile = true;
    }
  }

  public String formatta() {
    return id + ";" + nome + ";" + descrizione + ";" + tipologia + ";" + durata + ";" + disponibile;
  }

  @Override
  public String toString() {
    return "Obbiettivo{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", descrizione='" + descrizione + '\'' +
        ", tipologia='" + tipologia + '\'' +
        ", durata=" + durata +
        ", disponibile='" + disponibile + '\'' +
        '}';
  }
}
