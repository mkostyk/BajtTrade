package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.strategieRobotników.strategieKupna.StrategiaKupna;
import main.symulacja.strategieRobotników.strategiePracy.StrategiaPracy;
import main.symulacja.strategieRobotników.strategieProdukcji.StrategiaProdukcji;

import java.util.Map;

@SuppressWarnings("UnusedDeclaration")
public class RobotnikAdapter {
    private static class RobotnikJson {
        private int id;
        private int poziom;
        private String kariera;
        private StrategiaKupna kupowanie;
        private StrategiaProdukcji produkcja;
        private StrategiaPracy uczenie;
        private String zmiana;
        private Map<String, Integer> produktywnosc;
        private Map<String, Double> zasoby;
    }

    @ToJson
    private String toJson(Robotnik robotnik) {
        return robotnik.toString();
    }

    @FromJson
    private Robotnik fromJson(RobotnikJson robotnik) {
        return new Robotnik(robotnik.id, robotnik.poziom, robotnik.kariera, robotnik.kupowanie, robotnik.produkcja,
                            robotnik.uczenie, robotnik.zmiana, robotnik.produktywnosc, robotnik.zasoby);
    }
}
