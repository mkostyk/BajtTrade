package main.symulacja.utils;

import main.symulacja.Symulacja;
import main.symulacja.produkty.Produkt;
import java.util.Arrays;
import java.util.TreeMap;

public class PodsumowanieDnia {
    private final TreeMap<Produkt, Double> średnie;
    private final TreeMap<Produkt, Double> cenyZerowe;
    private final TreeMap<Produkt, Double> najniższeCeny;
    private final int[] ofertySprzedażySpekulantów;
    private final int[] ofertySprzedażyRobotników;

    public PodsumowanieDnia(TreeMap<Produkt, Double> średnie, TreeMap<Produkt, Double> cenyZerowe,
                            int[] ofertySprzedażySpekulantów, int[] ofertySprzedażyRobotników,
                            TreeMap<Produkt, Double> najniższeCeny) {
        this.średnie = średnie;
        this.cenyZerowe = cenyZerowe;
        this.ofertySprzedażySpekulantów = ofertySprzedażySpekulantów;
        this.ofertySprzedażyRobotników = ofertySprzedażyRobotników;
        this.najniższeCeny = najniższeCeny;
    }

    public double podajŚredniąCenę(Symulacja.TypyProduktów typ, int poziom) {
        // TODO - ładniej to
        if (typ == Symulacja.TypyProduktów.DIAMENTY) {
            return 0;
        }

        Produkt produkt = new Produkt(typ, poziom);
        if (średnie.get(produkt) == null) {
            return cenyZerowe.get(new Produkt(typ, 1));
        } else {
            return średnie.get(produkt);
        }
    }

    // TODO - standaryzacja
    public double podajŚredniąCenę(Produkt produkt) {
        return podajŚredniąCenę(produkt.podajTyp(), produkt.podajPoziom());
    }

    public double podajNajniższąCenę(Produkt produkt) {
        if (produkt.podajTyp() == Symulacja.TypyProduktów.DIAMENTY) {
            return 0;
        }

        if (najniższeCeny.get(produkt) == null) {
            return cenyZerowe.get(new Produkt(produkt.podajTyp(), 1));
        } else {
            return najniższeCeny.get(produkt);
        }
    }

    public int podajLiczbęOfertSprzedażyRobotników(Symulacja.TypyProduktów produkt) {
        if (produkt == Symulacja.TypyProduktów.DIAMENTY) {
            return 0;
        }

        int id = Symulacja.ID_PRODUKTU.get(produkt);
        return ofertySprzedażyRobotników[id];
    }

    public int podajLiczbęOfertSprzedaży(Symulacja.TypyProduktów produkt) {
        if (produkt == Symulacja.TypyProduktów.DIAMENTY) {
            return 0;
        }

        int id = Symulacja.ID_PRODUKTU.get(produkt);
        return ofertySprzedażyRobotników[id] + ofertySprzedażySpekulantów[id];
    }

    @Override
    public String toString() {
        return "PodsumowanieDnia{" +
                "średnie=" + średnie.toString() +
                ", ofertySprzedażySpekulantów=" + Arrays.toString(ofertySprzedażySpekulantów) +
                ", ofertySprzedażyRobotników=" + Arrays.toString(ofertySprzedażyRobotników) +
                '}';
    }
}
