package main.symulacja.strategieRobotników;

import main.symulacja.agenci.robotnicy.Robotnik;

public class Strategia {
    protected transient Robotnik robotnik;

    public void ustawRobotnika(Robotnik robotnik) {
        this.robotnik = robotnik;
    }
}
