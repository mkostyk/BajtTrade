package main.symulacja.strategieRobotników.strategieKupna;

import main.symulacja.giełda.oferty.OfertaRobotnika;

import static main.symulacja.Symulacja.TypyProduktów.*;

public class Gadżeciarz extends Zmechanizowany {
    public Gadżeciarz(int liczbaNarzędzi) {
        super(liczbaNarzędzi);
    }

    @Override
    public void dokonajZakupów() {
        super.dokonajZakupów();
        robotnik.podajGiełdę().dodajOfertęKupnaRobotnika(new OfertaRobotnika(PROGRAMY, robotnik.ileProgramówBrakuje(), robotnik));
    }
}
