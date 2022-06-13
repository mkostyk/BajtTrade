package main.symulacja.strategieRobotników.strategieKupna;

import static main.Main.TypyProduktów.*;
import main.symulacja.giełda.oferty.OfertaRobotnika;

public class Technofob extends StrategiaKupna {
    @Override
    public void dokonajZakupów() {
        robotnik.podajGiełdę().dodajOfertęKupnaRobotnika(new OfertaRobotnika(JEDZENIE, 100, robotnik));
    }
}
