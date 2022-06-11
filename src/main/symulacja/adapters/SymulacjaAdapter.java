package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.agenci.spekulanci.Spekulant;

import java.util.List;
import java.util.Map;

public class SymulacjaAdapter {
    private static class SymulacjaJson {
        private int dlugosc;
        private int kara_za_brak_ubran;
        private Map<String, Double> ceny;
        private List<Robotnik> robotnicy;
        private List<Spekulant> spekulanci;
        private String gielda;
    }

    @ToJson
    public String toJson(Symulacja symulacja) {
        // TODO
        return null;
    }

    @FromJson
    public Symulacja fromJson(SymulacjaJson symulacja) {
        return new Symulacja(symulacja.dlugosc, symulacja.gielda, symulacja.kara_za_brak_ubran,
                             symulacja.ceny, symulacja.robotnicy, symulacja.spekulanci);
    }
}
