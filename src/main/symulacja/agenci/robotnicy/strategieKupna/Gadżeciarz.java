package main.symulacja.agenci.robotnicy.strategieKupna;

import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.giełda.oferty.OfertaRobotnika;

import static main.symulacja.Symulacja.TypyProduktów.*;

public class Gadżeciarz extends Zmechanizowany {

    public Gadżeciarz(Robotnik robotnik, int liczbaNarzędzi) {
        super(robotnik, liczbaNarzędzi);
    }

    @Override
    public void dokonajZakupów() {
        super.dokonajZakupów();
        robotnik.podajGiełdę().dodajOfertęKupnaRobotnika(new OfertaRobotnika(PROGRAMY, robotnik.ileProgramówBrakuje(), robotnik));
    }
}
