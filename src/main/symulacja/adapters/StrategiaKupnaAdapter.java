package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.agenci.robotnicy.scieżkiKariery.ŚcieżkaKariery;
import main.symulacja.fabryka.Fabryka;
import main.symulacja.strategieRobotników.strategieKupna.StrategiaKupna;

public class StrategiaKupnaAdapter {
    private static class StrategiaKupnaJson {
        private String typ;
        private int liczba_narzedzi = -1;
    }

    @ToJson
    private String toJson(StrategiaKupna zakupy) {
        // TODO
        return null;
    }

    @FromJson
    private StrategiaKupna fromJson(StrategiaKupnaJson zakupy) {
        return Fabryka.stwórzStrategięKupna(zakupy.typ, zakupy.liczba_narzedzi);
    }
}
