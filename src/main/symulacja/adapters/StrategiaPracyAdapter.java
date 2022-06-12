package main.symulacja.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.fabryka.Fabryka;
import main.symulacja.strategieRobotników.strategiePracy.StrategiaPracy;
import main.symulacja.strategieRobotników.strategieProdukcji.StrategiaProdukcji;

public class StrategiaPracyAdapter {
    private static class StrategiaPracyJson {
        private String typ;
        private int limit_diamentów = -1;
        private int zapas = -1;
        private int okres = -1;
        private int okresowość_nauki = -1;
    }

    @ToJson
    private String toJson(StrategiaPracy praca) {
        // TODO
        return null;
    }

    @FromJson
    private StrategiaPracy fromJson(StrategiaPracyJson praca) {
        return Fabryka.stwórzStrategięPracy(praca.typ, praca.limit_diamentów, praca.zapas,
                                            praca.okres, praca.okresowość_nauki);
    }
}
