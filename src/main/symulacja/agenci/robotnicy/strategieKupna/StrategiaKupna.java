package main.symulacja.agenci.robotnicy.strategieKupna;

import main.symulacja.agenci.robotnicy.Robotnik;

public abstract class StrategiaKupna {
    protected Robotnik robotnik;

    public StrategiaKupna(Robotnik robotnik) {
        this.robotnik = robotnik;
    }
    public abstract void dokonajZakupów();
}
