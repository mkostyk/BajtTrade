package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.fabryka.Fabryka;

import java.util.Map;

@SuppressWarnings("UnusedDeclaration")
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
