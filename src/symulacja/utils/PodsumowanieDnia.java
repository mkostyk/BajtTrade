package symulacja.utils;

import symulacja.Symulacja;

public class PodsumowanieDnia {
    private final double[] średnie;
    private final int[] ofertySprzedaży;

    public PodsumowanieDnia(double[] średnie, int[] ofertySprzedaży) {
        this.średnie = średnie;
        this.ofertySprzedaży = ofertySprzedaży;
    }

    public double podajŚredniąCenę(Symulacja.TypyProduktów produkt) {
        int id = Symulacja.ID_PRODUKTU.get(produkt);
        return średnie[id];
    }

    public int podajLiczbęOfertSprzedaży(Symulacja.TypyProduktów produkt) {
        int id = Symulacja.ID_PRODUKTU.get(produkt);
        return ofertySprzedaży[id];
    };
}
