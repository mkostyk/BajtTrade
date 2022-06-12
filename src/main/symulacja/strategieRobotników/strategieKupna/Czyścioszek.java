package main.symulacja.strategieRobotników.strategieKupna;

import main.symulacja.giełda.oferty.OfertaRobotnika;

import static main.symulacja.Symulacja.TypyProduktów.*;

public class Czyścioszek extends Technofob {
    @Override
    public void dokonajZakupów() {
        super.dokonajZakupów();
        int ileUbrańPotrzebne = Math.max(0, robotnik.ileUbrańPotrzebneNaJutro());
        robotnik.podajGiełdę().dodajOfertęKupnaRobotnika(new OfertaRobotnika(UBRANIA, ileUbrańPotrzebne, robotnik));
    }
}
