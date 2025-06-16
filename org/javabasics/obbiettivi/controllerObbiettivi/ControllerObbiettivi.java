package org.javabasics.obbiettivi.controllerObbiettivi;

import java.util.Map;

import org.javabasics.obbiettivi.modelObbiettivi.Obbiettivo;

public class ControllerObbiettivi {
 public static void stampaObbiettivi(Map<Integer, Obbiettivo> obbiettivi) {
    for (Obbiettivo o : obbiettivi.values()) {
        System.out.println(o.toString());
    }
}
}