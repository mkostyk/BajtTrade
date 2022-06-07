package main.symulacja.agenci.robotnicy.strategieKariery;

import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;

public abstract class StrategiaKariery {
    protected Robotnik robotnik;

    public abstract boolean czyZmienia();
    public abstract Symulacja.Zawody podajNowyZawód();
}
