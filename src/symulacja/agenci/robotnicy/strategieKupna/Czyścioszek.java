package symulacja.agenci.robotnicy.strategieKupna;

import symulacja.agenci.robotnicy.Robotnik;
import symulacja.giełda.oferty.OfertaRobotnika;

import static symulacja.Symulacja.TypyProduktów.*;

public class Czyścioszek extends Technofob {
    public Czyścioszek(Robotnik robotnik) {
        super(robotnik);
    }

    @Override
    public void dokonajZakupów() {
        super.dokonajZakupów();
        int ileUbrańPotrzebne = Math.max(0, 100 - robotnik.ileUbrańJutro());
        robotnik.podajGiełdę().dodajOfertęKupnaRobotnika(new OfertaRobotnika(UBRANIA, ileUbrańPotrzebne));
    }
}
