package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.scieżkiKariery.ŚcieżkaKariery;

public class ŚcieżkaKarieryAdapter {
    private class ŚcieżkaKarieryJson {
        private String typ;
        private int poziom = 0;
    }

    @ToJson
    public String toJson(ŚcieżkaKariery kariera) {
        // TODO
        return null;
    }

    @FromJson
    public ŚcieżkaKariery fromJson()
}
