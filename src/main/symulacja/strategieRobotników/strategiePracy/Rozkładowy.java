package main.symulacja.strategieRobotników.strategiePracy;

import static main.symulacja.Symulacja.RNG;

public class Rozkładowy extends StrategiaPracy {
    @Override
    public boolean czyPracuje() {
        double szansaNaNaukę = 1 - 1/((double) robotnik.podajDzień() + 3);
        return (RNG.nextDouble() > szansaNaNaukę);
    }
}
