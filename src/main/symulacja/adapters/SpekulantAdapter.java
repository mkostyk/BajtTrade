package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.fabryka.Fabryka;
import main.symulacja.produkty.Produkt;
import main.symulacja.strategieRobotników.strategieKariery.StrategiaKariery;
import main.symulacja.strategieRobotników.strategieKupna.StrategiaKupna;
import main.symulacja.strategieRobotników.strategiePracy.StrategiaPracy;
import main.symulacja.strategieRobotników.strategieProdukcji.StrategiaProdukcji;

import java.util.Map;

public class SpekulantAdapter {
    private static class SpekulantJson {
        private int id;
        private String kariera;
        private Map <String, Double> zasoby;
        private int historia_spekulanta_sredniego;
    }

    @ToJson
    private String toJson(Spekulant spekulant) {
        return spekulant.toString();
    }

    @FromJson
    private Spekulant fromJson(SpekulantJson spekulant) {
        return Fabryka.stwórzSpekulanta(spekulant.id, spekulant.kariera, spekulant.zasoby, spekulant.historia_spekulanta_sredniego);
    }
}
