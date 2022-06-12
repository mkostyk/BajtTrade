package main.symulacja.strategieRobotników.strategieKupna;

import main.symulacja.giełda.oferty.OfertaRobotnika;

import static main.symulacja.Symulacja.TypyProduktów.*;

// TODO - ubrania są zjebane
public class Czyścioszek extends Technofob {
    @Override
    public void dokonajZakupów() {
        super.dokonajZakupów();
        int ileUbrańPotrzebne = Math.max(0, 100 - robotnik.ileUbrańJutro());
        robotnik.podajGiełdę().dodajOfertęKupnaRobotnika(new OfertaRobotnika(UBRANIA, ileUbrańPotrzebne, robotnik));
    }
}
