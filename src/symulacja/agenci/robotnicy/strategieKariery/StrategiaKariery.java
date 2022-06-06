package symulacja.agenci.robotnicy.strategieKariery;

import symulacja.Symulacja;
import symulacja.agenci.robotnicy.Robotnik;

public abstract class StrategiaKariery {
    protected Robotnik robotnik;

    public abstract boolean czyZmienia();
    public abstract Symulacja.Zawody podajNowyZawód();
}
