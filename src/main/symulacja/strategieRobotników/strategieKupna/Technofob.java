package main.symulacja.strategieRobotników.strategieKupna;

import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.giełda.oferty.OfertaRobotnika;

import static main.symulacja.Symulacja.TypyProduktów.JEDZENIE;

public class Technofob extends StrategiaKupna {
    @Override
    public void dokonajZakupów() {
        robotnik.podajGiełdę().dodajOfertęKupnaRobotnika(new OfertaRobotnika(JEDZENIE, 100, robotnik));
    }
}
