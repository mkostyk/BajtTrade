package symulacja.agenci.robotnicy.strategieKupna;

import symulacja.agenci.robotnicy.Robotnik;
import symulacja.giełda.oferty.OfertaRobotnika;

import static symulacja.Symulacja.TypyProduktów.*;

public class Gadżeciarz extends Zmechanizowany {

    public Gadżeciarz(Robotnik robotnik, int liczbaNarzędzi) {
        super(robotnik, liczbaNarzędzi);
    }

    @Override
    public void dokonajZakupów() {
        super.dokonajZakupów();
        robotnik.podajGiełdę().dodajOfertęKupnaRobotnika(new OfertaRobotnika(PROGRAMY, robotnik.ileProgramówBrakuje()));
    }
}
