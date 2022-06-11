package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.agenci.robotnicy.scieżkiKariery.ŚcieżkaKariery;
import main.symulacja.fabryka.Fabryka;
import main.symulacja.giełda.Giełda;
import main.symulacja.produkty.Produkt;
import main.symulacja.strategieRobotników.strategieKariery.StrategiaKariery;
import main.symulacja.strategieRobotników.strategieKupna.StrategiaKupna;
import main.symulacja.strategieRobotników.strategiePracy.StrategiaPracy;
import main.symulacja.strategieRobotników.strategieProdukcji.StrategiaProdukcji;

import java.util.Map;

public class RobotnikAdapter {
    private static class RobotnikJson {
        private int id;
        private int poziom;
        private String kariera;
        private StrategiaKupna kupowanie;
        private StrategiaProdukcji produkcja;
        private StrategiaPracy uczenie;
        private StrategiaKariery zmiana;
        private Map<Symulacja.Zawody, Integer> produktywnosc;
        private Map<Produkt, Double> zasoby;
    }

    @ToJson
    public String toJson(Symulacja symulacja) {
        // TODO
        return null;
    }

    @FromJson
    public Robotnik fromJson(RobotnikJson robotnik) {
        return new Robotnik(robotnik.id, robotnik.poziom, robotnik.kariera, robotnik.kupowanie, robotnik.produkcja,
                            robotnik.uczenie, robotnik.zmiana, robotnik.produktywnosc, robotnik.zasoby);
    }
}
