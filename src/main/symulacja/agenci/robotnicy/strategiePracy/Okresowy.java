package main.symulacja.agenci.robotnicy.strategiePracy;

import main.symulacja.agenci.robotnicy.Robotnik;

public class Okresowy extends StrategiaPracy {
    private final int okresNauki;

    public Okresowy (Robotnik robotnik, int okresNauki) {
        super(robotnik);
        this.okresNauki = okresNauki;
    }

    @Override
    public boolean czyPracuje() {
        return (robotnik.podajDzień() % okresNauki == 0);
    }
}
