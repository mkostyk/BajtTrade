package symulacja.utils;

import symulacja.Symulacja;
import symulacja.produkty.Produkt;

public class PodsumowanieDnia {
    private final double[] średnie;

    public PodsumowanieDnia(double[] średnie) {
        this.średnie = średnie;
    }

    public double podajŚredniąCenę(String produkt) {
        int id = Symulacja.ID_PRODUKTU.get(produkt);
        return średnie[id];
    }
}
