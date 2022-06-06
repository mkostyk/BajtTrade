package symulacja.giełda.oferty;

import symulacja.Symulacja;

public class OfertaRobotnika {
    private Symulacja.TypyProduktów produkt;
    private int ile;

    public OfertaRobotnika (Symulacja.TypyProduktów produkt, int ile) {
        this.produkt = produkt;
        this.ile = ile;
    }

    public Symulacja.TypyProduktów getProdukt() {
        return produkt;
    }

    public int getIle() {
        return ile;
    }
}
