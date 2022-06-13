package main.symulacja.strategieRobotników.strategieKariery;

import static main.Main.Zawody;
import main.symulacja.strategieRobotników.Strategia;

public abstract class StrategiaKariery extends Strategia {
    public abstract boolean czyZmienia();
    public abstract Zawody podajNowyZawód();
}
