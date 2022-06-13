package main.symulacja.strategieRobotników.strategiePracy;

import main.Main;
import main.symulacja.Symulacja;

public class Rozkładowy extends StrategiaPracy {
    @Override
    public boolean czyPracuje() {
        double szansaNaNaukę = 1 - 1/((double) robotnik.podajDzień() + 3);
        return (Main.RNG.nextDouble() > szansaNaNaukę);
    }
}
