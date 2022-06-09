package main.symulacja.strategieRobotników.strategiePracy;

import main.symulacja.agenci.robotnicy.Robotnik;

public class Oszczędny extends StrategiaPracy {
    private final double limitDiamentów;

    public Oszczędny(double limitDiamentów) {
        this.limitDiamentów = limitDiamentów;
    }

    @Override
    public boolean czyPracuje() {
        return (robotnik.ileDiamentów() <= limitDiamentów);
    }
}
