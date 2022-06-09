package main.symulacja.strategieRobotników.strategieKariery;

import main.symulacja.Symulacja;

public class Konserwatysta extends StrategiaKariery {
    @Override
    public boolean czyZmienia() {
        return false;
    }

    @Override
    public Symulacja.Zawody podajNowyZawód() {
        return null;
    }
}
