package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.utils.BazoweInformacje;

import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public class SymulacjaAdapter {
    private static class SymulacjaJson {
        private BazoweInformacje info;
        private List<Robotnik> robotnicy;
        private List<Spekulant> spekulanci;
    }

    @ToJson
    private String toJson(Symulacja symulacja) {
        return symulacja.toString();
    }

    @FromJson
    private Symulacja fromJson(SymulacjaJson symulacja) {
        return new Symulacja(symulacja.info, symulacja.robotnicy, symulacja.spekulanci);
    }
}
