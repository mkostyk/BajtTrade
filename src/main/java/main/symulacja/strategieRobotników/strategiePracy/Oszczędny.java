package main.symulacja.strategieRobotników.strategiePracy;

import com.squareup.moshi.Json;

public class Oszczędny extends StrategiaPracy {
    @Json(name = "limit_diamentow")
    private final double limitDiamentów;

    public Oszczędny(double limitDiamentów) {
        this.limitDiamentów = limitDiamentów;
    }

    @Override
    public boolean czyPracuje() {
        return (robotnik.ileDiamentów() <= limitDiamentów);
    }
}
