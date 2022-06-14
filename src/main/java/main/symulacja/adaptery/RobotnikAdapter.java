package main.symulacja.adaptery;

import main.Main;
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
        protected int id;
        protected int poziom;
        protected String kariera;
        protected StrategiaKupna kupowanie;
        protected StrategiaProdukcji produkcja;
        protected StrategiaPracy uczenie;
        protected String zmiana;
        protected Map<String, Integer> produktywnosc;
        
    }
    
    private static class RobotnikInputJson extends RobotnikJson {
        private Map<String, Double> zasoby;
    }

    private static class RobotnikOutputJson extends RobotnikJson {
        private Map<String, Double[]> zasoby;
    }

    @ToJson
    private RobotnikOutputJson toJson(Robotnik robotnik) {
        RobotnikOutputJson json = new RobotnikOutputJson();
        json.id = robotnik.podajID();
        json.poziom = robotnik.podajKarierę().podajPoziom();
        json.kariera = Main.enumToString(robotnik.podajKarierę().podajZawód());
        json.kupowanie = robotnik.podajStrategięKupna();
        json.produkcja = robotnik.podajStrategięProdukcji();
        json.uczenie = robotnik.podajStrategięPracy();
        json.zmiana = robotnik.podajStrategięKariery().toString();
        json.produktywnosc = robotnik.podajBazoweProduktywności();
        json.zasoby = Main.stwórzMapęTablicProduktów(robotnik);
        return json;
    }

    @FromJson
    private Robotnik fromJson(RobotnikInputJson robotnik) {
        return new Robotnik(robotnik.id, robotnik.poziom, robotnik.kariera, robotnik.kupowanie, robotnik.produkcja,
                            robotnik.uczenie, robotnik.zmiana, robotnik.produktywnosc, robotnik.zasoby);
    }
}
