package main.symulacja.agenci.robotnicy.strategieKupna;

import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.giełda.oferty.OfertaRobotnika;

import static main.symulacja.Symulacja.TypyProduktów.*;

public class Czyścioszek extends Technofob {
    public Czyścioszek(Robotnik robotnik) {
        super(robotnik);
    }

    @Override
    public void dokonajZakupów() {
        super.dokonajZakupów();
        int ileUbrańPotrzebne = Math.max(0, 100 - robotnik.ileUbrańJutro());
        robotnik.podajGiełdę().dodajOfertęKupnaRobotnika(new OfertaRobotnika(UBRANIA, ileUbrańPotrzebne, robotnik));
    }
}
