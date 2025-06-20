

# 🧘‍♂️ MeditActive

**MeditActive** è un'applicazione Java da linea di comando per la gestione di obiettivi, prenotazioni e utenti in un contesto di benessere/meditazione. Utilizza file CSV per la persistenza dei dati.

## 📁 Struttura del progetto

```
org/
└── javabasics/
    ├── Application.java
    ├── mainController/
    ├── mainService/
    ├── obbiettivi/
    │   ├── modelObbiettivi/
    │   ├── repositoryObbiettivi/
    │   └── serviceObbiettivi/
    ├── prenotazioni/
    │   ├── modelPrenotazioni/
    │   ├── repositoryPrenotazioni/
    │   └── servicePrenotazioni/
    ├── utenti/
    │   ├── modelUtente/
    │   ├── repositoryUtente/
    │   └── serviceUtente/
    └── dati/
        ├── utenti.csv
        ├── prenotazioni.csv
        └── obiettivi.csv
```

## 📦 Funzionalità principali

* **Visualizzazione degli obiettivi disponibili**
* **Prenotazione di un obiettivo da parte di un utente**
* **Cancellazione di una prenotazione**
* **Registrazione di un nuovo utente**
* **Esportazione degli obiettivi disponibili in un file CSV con timestamp**

## 📄 Dipendenze

* Java 17+
* Nessuna libreria esterna (tutto è gestito con classi standard Java)

## ▶️ Avvio

Compila ed esegui la classe `Application.java`. Seguirà un'interfaccia testuale per scegliere le operazioni.

```bash
javac org/javabasics/Application.java
java org.javabasics.Application
```

## 🗃️ Persistenza dei dati

I dati sono salvati in file CSV all'interno della cartella `org/javabasics/dati/`. Ogni file ha un'intestazione ed è aggiornato a ogni modifica.

## 🧪 Validazioni

* I campi ID devono essere presenti e numerici.
* Le date devono essere in formato `dd/MM/yyyy`.
* I file vengono controllati per esistenza all'avvio.
* Le prenotazioni durano 7 giorni a partire dalla data di prenotazione.


## 📌 Note

Il percorso dei file è relativo alla struttura del progetto (`org/javabasics/dati/...`) per una maggiore portabilità.
Certo! Ecco un esempio di sezione **Autore** da includere nel README del tuo progetto Java. Puoi inserirla verso la fine del file README, magari prima o dopo la sezione Licenza.


## Autore

**Gioele Boccanegra**
GitHub: [github.com/gioeleboccanegra](https://github.com/gioeleboccanegra)

---



