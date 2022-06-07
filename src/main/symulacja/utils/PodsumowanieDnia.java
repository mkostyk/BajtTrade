package main.symulacja.utils;

import main.symulacja.Symulacja;

public class PodsumowanieDnia {
    private final double[] średnie;
    private final int[] ofertySprzedażySpekulantów;
    private final int[] ofertySprzedażyRobotników;

    public PodsumowanieDnia(double[] średnie, int[] ofertySprzedażySpekulantów, int[] ofertySprzedażyRobotników) {
        this.średnie = średnie;
        this.ofertySprzedażySpekulantów = ofertySprzedażySpekulantów;
        this.ofertySprzedażyRobotników = ofertySprzedażyRobotników;
    }

    public double podajŚredniąCenę(Symulacja.TypyProduktów produkt) {
        int id = Symulacja.ID_PRODUKTU.get(produkt);
        return średnie[id];
    }

    public int podajLiczbęOfertSprzedażyRobotników(Symulacja.TypyProduktów produkt) {
        int id = Symulacja.ID_PRODUKTU.get(produkt);
        return ofertySprzedażyRobotników[id];
    };

    public int podajLiczbęOfertSprzedaży(Symulacja.TypyProduktów produkt) {
        int id = Symulacja.ID_PRODUKTU.get(produkt);
        return ofertySprzedażyRobotników[id] + ofertySprzedażySpekulantów[id];
    };
}
