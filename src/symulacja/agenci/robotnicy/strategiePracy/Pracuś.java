package symulacja.agenci.robotnicy.strategiePracy;

import symulacja.agenci.robotnicy.Robotnik;

public class Pracuś extends StrategiaPracy {
    public Pracuś(Robotnik robotnik) {
        super(robotnik);
    }

    @Override
    public boolean czyPracuje() {
        return true;
    }
}
