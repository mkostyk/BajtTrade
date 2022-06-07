package main.symulacja.agenci.robotnicy.strategiePracy;

import main.symulacja.agenci.robotnicy.Robotnik;

public class Oszczędny extends StrategiaPracy {
    private final double limitDiamentów;

    public Oszczędny(Robotnik robotnik, double limitDiamentów) {
        super(robotnik);
        this.limitDiamentów = limitDiamentów;
    }

    @Override
    public boolean czyPracuje() {
        return (robotnik.ileDiamentów() <= limitDiamentów);
    }
}
