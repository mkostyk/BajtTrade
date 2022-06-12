package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.scieżkiKariery.ŚcieżkaKariery;
import main.symulacja.fabryka.Fabryka;

public class ŚcieżkaKarieryAdapter {
    private class ŚcieżkaKarieryJson {
        private String typ;
        private int poziom = 0;
    }

    @ToJson
    private String toJson(ŚcieżkaKariery kariera) {
        // TODO
        return null;
    }

    @FromJson
    private ŚcieżkaKariery fromJson(ŚcieżkaKarieryJson kariera) {
        return Fabryka.stwórzŚcieżkęKariery(kariera.typ, kariera.poziom);
    }
}
