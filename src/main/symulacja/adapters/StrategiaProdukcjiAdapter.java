package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.fabryka.Fabryka;
import main.symulacja.strategieRobotników.strategieProdukcji.StrategiaProdukcji;

@SuppressWarnings("UnusedDeclaration")
public class StrategiaProdukcjiAdapter {
    private static class StrategiaProdukcjiJson {
        private String typ;
        private int historia_sredniej_produkcji;
        private int historia_perspektywy;
    }

    @ToJson
    private String toJson(StrategiaProdukcji produkcja) {
        return produkcja.toString();
    }

    @FromJson
    private StrategiaProdukcji fromJson(StrategiaProdukcjiJson produkcja) {
        return Fabryka.stwórzStrategięProdukcji(produkcja.typ, produkcja.historia_sredniej_produkcji,
                                                produkcja.historia_perspektywy);
    }
}
