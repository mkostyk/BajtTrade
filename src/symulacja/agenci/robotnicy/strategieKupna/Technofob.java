package symulacja.agenci.robotnicy.strategieKupna;

import symulacja.agenci.robotnicy.Robotnik;
import symulacja.giełda.oferty.OfertaRobotnika;

import static symulacja.Symulacja.TypyProduktów.JEDZENIE;

public class Technofob extends StrategiaKupna {
    public Technofob(Robotnik robotnik) {
        super(robotnik);
    }

    @Override
    public void dokonajZakupów() {
        robotnik.podajGiełdę().dodajOfertęKupnaRobotnika(new OfertaRobotnika(JEDZENIE, 100));
    }
}
