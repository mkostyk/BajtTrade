package symulacja.agenci.robotnicy.strategieKupna;

import symulacja.agenci.robotnicy.Robotnik;
import symulacja.giełda.oferty.OfertaRobotnika;

import static symulacja.Symulacja.TypyProduktów.*;

public class Zmechanizowany extends Czyścioszek {
    protected final int liczbaNarzędzi;

    public Zmechanizowany(Robotnik robotnik, int liczbaNarzędzi) {
        super(robotnik);
        this.liczbaNarzędzi = liczbaNarzędzi;
    }

    @Override
    public void dokonajZakupów() {
        super.dokonajZakupów();
        robotnik.podajGiełdę().dodajOfertęKupnaRobotnika(new OfertaRobotnika(NARZĘDZIA, liczbaNarzędzi, robotnik));
    }
}
