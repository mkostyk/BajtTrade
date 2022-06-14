package main.symulacja.strategieRobotników.strategiePracy;

import main.symulacja.produkty.Produkt;

import static main.Main.TypyProduktów.*;

public class Student extends StrategiaPracy {
    private final int zapas;
    private final int okres;

    public Student(int zapas, int okres) {
        this.zapas = zapas;
        this.okres = okres;
    }

    @Override
    public boolean czyPracuje() {
        double cenaJedzenia = robotnik.podajGiełdę().podajŚredniąCenęProduktu(okres, new Produkt(JEDZENIE, 1));
        return (robotnik.ileDiamentów() <= zapas * 100 * cenaJedzenia);
    }
}
