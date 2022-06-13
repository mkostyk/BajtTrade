package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.strategieRobotników.strategieKariery.StrategiaKariery;
import main.symulacja.fabryka.Fabryka;

@SuppressWarnings("UnusedDeclaration")
public class StrategiaKarieryAdapter {
    private static class StrategiaKarieryJson {
        private String typ;
    }

    @ToJson
    private String toJson(StrategiaKariery kariera) {
        return null;
    }

    @FromJson
    private StrategiaKariery fromJson(StrategiaKarieryJson strategiaKariery) {
        return Fabryka.stwórzStrategięKariery(strategiaKariery.typ);
    }
}
