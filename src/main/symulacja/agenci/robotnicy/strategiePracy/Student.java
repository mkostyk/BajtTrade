package main.symulacja.agenci.robotnicy.strategiePracy;

import main.symulacja.agenci.robotnicy.Robotnik;

import static main.symulacja.Symulacja.TypyProduktów.*;

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
