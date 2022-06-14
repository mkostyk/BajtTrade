package main.symulacja.strategieRobotników.strategieKupna;

import main.symulacja.giełda.oferty.OfertaRobotnika;

import static main.Main.TypyProduktów.UBRANIA;

public class Czyścioszek extends Technofob {
    @Override
    public void dokonajZakupów() {
        super.dokonajZakupów();
        int ileUbrańPotrzebne = robotnik.ileUbrańPotrzebneNaJutro();
        robotnik.podajGiełdę().dodajOfertęKupnaRobotnika(new OfertaRobotnika(UBRANIA, ileUbrańPotrzebne, robotnik));
    }
}
