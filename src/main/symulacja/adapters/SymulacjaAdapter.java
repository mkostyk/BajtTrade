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
    private SymulacjaJson toJson(Symulacja symulacja) {
        SymulacjaJson json = new SymulacjaJson();
        json.info = symulacja.podajInformacje();
        json.robotnicy = symulacja.podajRobotników();
        json.spekulanci = symulacja.podajSpekulantów();

        return json;
    }

    @FromJson
    private Symulacja fromJson(SymulacjaJson symulacja) {
        return new Symulacja(symulacja.info, symulacja.robotnicy, symulacja.spekulanci);
    }
}
