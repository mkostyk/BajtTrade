package main.symulacja.strategieRobotników.strategieKariery;

import static main.Main.Zawody;

public class Konserwatysta extends StrategiaKariery {
    @Override
    public boolean czyZmienia() {
        return false;
    }

    @Override
    public Zawody podajNowyZawód() {
        return null;
    }

    @Override
    public String toString() {
        return "konserwatysta";
    }
}
