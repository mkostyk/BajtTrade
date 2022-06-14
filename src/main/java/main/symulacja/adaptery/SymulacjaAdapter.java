package main.symulacja.adaptery;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.utils.BazoweInformacjeInput;
import main.symulacja.utils.BazoweInformacjeOutput;

import java.util.List;

@SuppressWarnings("UnusedDeclaration")
public class SymulacjaAdapter {
    private static class SymulacjaJson {
        protected List<Robotnik> robotnicy;
        protected List<Spekulant> spekulanci;
    }

    private static class SymulacjaInputJson extends SymulacjaJson {
        private BazoweInformacjeInput info;
    }

    private static class SymulacjaOutputJson extends SymulacjaJson {
        private BazoweInformacjeOutput info;
    }

    @ToJson
    private SymulacjaOutputJson toJson(Symulacja symulacja) {
        SymulacjaOutputJson json = new SymulacjaOutputJson();
        json.info = symulacja.podajInformacje();
        json.robotnicy = symulacja.podajRobotników();
        json.spekulanci = symulacja.podajSpekulantów();

        return json;
    }

    @FromJson
    private Symulacja fromJson(SymulacjaInputJson symulacja) {
        return new Symulacja(symulacja.info, symulacja.robotnicy, symulacja.spekulanci);
    }
}
