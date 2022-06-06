package symulacja.agenci.robotnicy.strategiePracy;

import symulacja.agenci.robotnicy.Robotnik;
import static symulacja.Symulacja.RNG;

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
