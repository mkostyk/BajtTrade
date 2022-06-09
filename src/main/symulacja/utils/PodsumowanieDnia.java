package main.symulacja.utils;

import com.sun.source.tree.Tree;
import main.symulacja.Symulacja;
import main.symulacja.komparatory.KomparatorProduktów;
import main.symulacja.produkty.Produkt;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class PodsumowanieDnia {
    private final TreeMap<Produkt, Double> średnie;
    private final int[] ofertySprzedażySpekulantów;
    private final int[] ofertySprzedażyRobotników;

    public PodsumowanieDnia(TreeMap<Produkt, Double> średnie, int[] ofertySprzedażySpekulantów, int[] ofertySprzedażyRobotników) {
        this.średnie = średnie;
        this.ofertySprzedażySpekulantów = ofertySprzedażySpekulantów;
        this.ofertySprzedażyRobotników = ofertySprzedażyRobotników;
    }

    public double podajŚredniąCenę(Symulacja.TypyProduktów typ, int poziom) {
        // TODO - ładniej to
        if (typ == Symulacja.TypyProduktów.DIAMENTY) {
            return 0;
        }

        Produkt produkt = new Produkt(typ, poziom);
        return średnie.get(produkt);
    }

    public int podajLiczbęOfertSprzedażyRobotników(Symulacja.TypyProduktów produkt) {
        if (produkt == Symulacja.TypyProduktów.DIAMENTY) {
            return 0;
        }

        int id = Symulacja.ID_PRODUKTU.get(produkt);
        return ofertySprzedażyRobotników[id];
    };

    public int podajLiczbęOfertSprzedaży(Symulacja.TypyProduktów produkt) {
        if (produkt == Symulacja.TypyProduktów.DIAMENTY) {
            return 0;
        }

        int id = Symulacja.ID_PRODUKTU.get(produkt);
        return ofertySprzedażyRobotników[id] + ofertySprzedażySpekulantów[id];
    };

    @Override
    public String toString() {
        return "PodsumowanieDnia{" +
                "średnie=" + średnie.toString() +
                ", ofertySprzedażySpekulantów=" + Arrays.toString(ofertySprzedażySpekulantów) +
                ", ofertySprzedażyRobotników=" + Arrays.toString(ofertySprzedażyRobotników) +
                '}';
    }
}
