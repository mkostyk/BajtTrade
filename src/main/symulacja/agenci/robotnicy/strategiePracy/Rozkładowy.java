package main.symulacja.agenci.robotnicy.strategiePracy;

import main.symulacja.agenci.robotnicy.Robotnik;
import static main.symulacja.Symulacja.RNG;

public class Rozkładowy extends StrategiaPracy {
    public Rozkładowy (Robotnik robotnik) {
        super(robotnik);
    }

    @Override
    public boolean czyPracuje() {
        double szansaNaNaukę = 1 - 1/((double) robotnik.podajDzień() + 3);
        return (RNG.nextDouble() > szansaNaNaukę);
    }
}
