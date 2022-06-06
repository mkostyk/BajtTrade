package symulacja.agenci.robotnicy.strategiePracy;

import symulacja.Symulacja;
import symulacja.agenci.robotnicy.Robotnik;

import java.util.Random;

public class Rozkładowy extends StrategiaPracy {
    public Rozkładowy (Robotnik robotnik) {
        super(robotnik);
    }

    @Override
    public boolean czyPracuje() {
        Random r = new Random();
        double szansaNaNaukę = 1 - 1/((double) robotnik.podajDzień() + 3);
        return (r.nextDouble() > szansaNaNaukę);
    }
}
