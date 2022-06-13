package main.symulacja.strategieRobotników.strategiePracy;

import com.squareup.moshi.Json;

public class Okresowy extends StrategiaPracy {
    @Json(name = "okresowosc_nauki")
    private final int okresNauki;

    public Okresowy (int okresNauki) {
        this.okresNauki = okresNauki;
    }

    @Override
    public boolean czyPracuje() {
        return (robotnik.podajDzień() % okresNauki == 0);
    }
}
