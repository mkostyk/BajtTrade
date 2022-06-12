package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.fabryka.Fabryka;
import main.symulacja.strategieRobotników.strategieKupna.StrategiaKupna;
import main.symulacja.strategieRobotników.strategieProdukcji.StrategiaProdukcji;

public class StrategiaProdukcjiAdapter {
    private static class StrategiaProdukcjiJson {
        private String typ;
        private int historia_sredniej_produkcji = -1;
        private int historia_perspektywy = -1;
    }

    @ToJson
    private String toJson(StrategiaProdukcji produkcja) {
        // TODO
        return null;
    }

    @FromJson
    private StrategiaProdukcji fromJson(StrategiaProdukcjiJson produkcja) {
        return Fabryka.stwórzStrategięProdukcji(produkcja.typ, produkcja.historia_sredniej_produkcji,
                                                produkcja.historia_perspektywy);
    }
}
