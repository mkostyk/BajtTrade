package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.utils.BazoweInformacje;

import java.util.List;
import java.util.Map;

public class SymulacjaAdapter {
    private static class SymulacjaJson {
        private BazoweInformacje info;
        private List<Robotnik> robotnicy;
        private List<Spekulant> spekulanci;
    }

    @ToJson
    private String toJson(Symulacja symulacja) {
        // TODO
        return symulacja.toString();
    }

    @FromJson
    private Symulacja fromJson(SymulacjaJson symulacja) {
        // TODO - użyć bazowych informacji
        return new Symulacja(symulacja.info.podajDługość(), symulacja.info.podajGiełdę(), symulacja.info.podajKaręZaBrakUbrań(),
                             symulacja.info.podajCeny(), symulacja.robotnicy, symulacja.spekulanci);
    }
}
