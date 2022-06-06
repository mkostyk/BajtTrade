package symulacja.agenci.robotnicy.strategiePracy;

import symulacja.Symulacja;
import symulacja.agenci.robotnicy.Robotnik;
import symulacja.utils.PodsumowanieDnia;
import static symulacja.Symulacja.TypyProduktów.*;


import java.util.Arrays;

public class Student extends StrategiaPracy {
    private final int zapas;
    private final int okres;

    public Student(Robotnik robotnik, int zapas, int okres) {
        super(robotnik);
        this.zapas = zapas;
        this.okres = okres;
    }

    @Override
    public boolean czyPracuje() {
        double cenaJedzenia = robotnik.podajGiełdę().podajŚredniąCenęProduktu(okres, JEDZENIE);
        return (robotnik.ileDiamentów() <= zapas * 100 * cenaJedzenia);
    }
}
