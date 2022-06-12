package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.fabryka.Fabryka;
import main.symulacja.strategieRobotników.strategiePracy.StrategiaPracy;

@SuppressWarnings("UnusedDeclaration")
public class StrategiaPracyAdapter {
    private static class StrategiaPracyJson {
        private String typ;
        private int limit_diamentow;
        private int zapas;
        private int okres;
        private int okresowosc_nauki;
    }

    @ToJson
    private String toJson(StrategiaPracy praca) {
        return praca.toString();
    }

    @FromJson
    private StrategiaPracy fromJson(StrategiaPracyJson praca) {
        return Fabryka.stwórzStrategięPracy(praca.typ, praca.limit_diamentow, praca.zapas,
                                            praca.okres, praca.okresowosc_nauki);
    }
}
