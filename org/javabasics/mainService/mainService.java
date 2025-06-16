package org.javabasics.mainService;

import org.javabasics.obbiettivi.serviceObbiettivi.ServiceObbiettivi;

public class MainService {
  public void ScegliOperazione(Integer risposta) {

    switch (risposta) {
      case 1:

        ServiceObbiettivi serviceObbiettivi = ServiceObbiettivi.getInstance();
        serviceObbiettivi.stampaObbiettivi();

        break;

    }

  }
}
