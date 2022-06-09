package main.symulacja.strategieRobotników.strategiePracy;

import main.symulacja.agenci.robotnicy.Robotnik;

import static main.symulacja.Symulacja.TypyProduktów.*;

public class Student extends StrategiaPracy {
    private final int zapas;
    private final int okres;

    public Student(int zapas, int okres) {
        this.zapas = zapas;
        this.okres = okres;
    }

    @Override
    public boolean czyPracuje() {
        double cenaJedzenia = robotnik.podajGiełdę().podajŚredniąCenęProduktu(okres, JEDZENIE, 1);
        return (robotnik.ileDiamentów() <= zapas * 100 * cenaJedzenia);
    }
}
