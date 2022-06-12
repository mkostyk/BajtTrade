package main.symulacja.strategieRobotników.strategiePracy;

public class Okresowy extends StrategiaPracy {
    private final int okresNauki;

    public Okresowy (int okresNauki) {
        this.okresNauki = okresNauki;
    }

    @Override
    public boolean czyPracuje() {
        return (robotnik.podajDzień() % okresNauki == 0);
    }
}
