package main.symulacja.strategieRobotników.strategieKariery;

import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.agenci.robotnicy.scieżkiKariery.*;
import main.symulacja.strategieRobotników.Strategia;

public abstract class StrategiaKariery extends Strategia {
    public abstract boolean czyZmienia();
    public abstract Symulacja.Zawody podajNowyZawód();
}
