package main.symulacja.strategieRobotników.strategieKariery;

import main.symulacja.Symulacja;
import main.symulacja.strategieRobotników.Strategia;

public abstract class StrategiaKariery extends Strategia {
    public abstract boolean czyZmienia();
    public abstract Symulacja.Zawody podajNowyZawód();
}
