package main.symulacja.agenci.robotnicy.strategieKupna;

import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.giełda.oferty.OfertaRobotnika;

import static main.symulacja.Symulacja.TypyProduktów.JEDZENIE;

public class Technofob extends StrategiaKupna {
    public Technofob(Robotnik robotnik) {
        super(robotnik);
    }

    @Override
    public void dokonajZakupów() {
        robotnik.podajGiełdę().dodajOfertęKupnaRobotnika(new OfertaRobotnika(JEDZENIE, 100, robotnik));
    }
}
