package main.symulacja.agenci.robotnicy.strategiePracy;

import main.symulacja.agenci.robotnicy.Robotnik;

public abstract class StrategiaPracy {
    protected Robotnik robotnik;

    public StrategiaPracy(Robotnik robotnik) {
        this.robotnik = robotnik;
    }

    public abstract boolean czyPracuje();
}
