package symulacja.agenci.robotnicy.strategiePracy;

import symulacja.agenci.robotnicy.Robotnik;
import symulacja.utils.PodsumowanieDnia;

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
        PodsumowanieDnia[] dane = robotnik.podajGiełdę().podajHistorięCen();

        int dzieńKońcowy = dane.length - 1;
        int dzieńStartowy = Math.max(dane.length - zapas, 0);

        PodsumowanieDnia[] potrzebneDane = Arrays.copyOfRange(dane, dzieńStartowy, dzieńKońcowy);
        double cenaJedzenia = 0;

        for(PodsumowanieDnia dzień: potrzebneDane) {
            cenaJedzenia += dzień.podajŚredniąCenę("Jedzenie");
        }

        cenaJedzenia /= okres;
        return (robotnik.ileDiamentów() <= zapas * 100 * cenaJedzenia);
    }
}
