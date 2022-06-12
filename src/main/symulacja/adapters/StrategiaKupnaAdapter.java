package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.fabryka.Fabryka;
import main.symulacja.strategieRobotników.strategieKupna.StrategiaKupna;

@SuppressWarnings("UnusedDeclaration")
public class StrategiaKupnaAdapter {
    private static class StrategiaKupnaJson {
        private String typ;
        private int liczba_narzedzi;
    }

    @ToJson
    private String toJson(StrategiaKupna zakupy) {
        return zakupy.toString();
    }

    @FromJson
    private StrategiaKupna fromJson(StrategiaKupnaJson zakupy) {
        return Fabryka.stwórzStrategięKupna(zakupy.typ, zakupy.liczba_narzedzi);
    }
}
