package main.symulacja.utils;

import main.Main;
import main.symulacja.Symulacja;
import main.symulacja.produkty.Produkt;
import java.util.Arrays;
import java.util.Map;

import static main.Main.PRODUKTY_NA_GIEŁDZIE;
import static main.Main.TypyProduktów;
import static main.Main.TypyProduktów.*;

public class PodsumowanieDnia {
    private final Map<Produkt, Double> średnie;
    private final Map<Produkt, Double> cenyZerowe;
    private final Map<Produkt, Double> najniższeCeny;
    private final Map<Produkt, Double> najwyższeCeny;
    private final int[] ofertySprzedażySpekulantów;
    private final int[] ofertySprzedażyRobotników;

    public PodsumowanieDnia(Map<Produkt, Double> średnie, Map<Produkt, Double> cenyZerowe,
                            int[] ofertySprzedażySpekulantów, int[] ofertySprzedażyRobotników,
                            Map<Produkt, Double> najniższeCeny, Map<Produkt, Double> najwyższeCeny) {
        this.średnie = średnie;
        this.cenyZerowe = cenyZerowe;
        this.ofertySprzedażySpekulantów = ofertySprzedażySpekulantów;
        this.ofertySprzedażyRobotników = ofertySprzedażyRobotników;
        this.najniższeCeny = najniższeCeny;
        this.najwyższeCeny = najwyższeCeny;
    }

    public double podajŚredniąCenę(Produkt produkt) {
        System.out.println(produkt.podajTyp() + " " + produkt.podajPoziom() + " " + cenyZerowe.get(new Produkt(produkt.podajTyp(), 1)));
        if (PRODUKTY_NA_GIEŁDZIE.contains(produkt.podajTyp())) {
            średnie.putIfAbsent(produkt, cenyZerowe.get(new Produkt(produkt.podajTyp(), 1)));
            return średnie.get(produkt);
        } else {
            return 0;
        }
    }

    public double podajNajniższąCenę(Produkt produkt) {
        if (PRODUKTY_NA_GIEŁDZIE.contains(produkt.podajTyp())) {
            najniższeCeny.putIfAbsent(produkt, cenyZerowe.get(new Produkt(produkt.podajTyp(), 1)));
            return najniższeCeny.get(produkt);
        } else {
            return 0;
        }
    }

    public int podajLiczbęOfertSprzedażyRobotników(TypyProduktów typ) {
        if (PRODUKTY_NA_GIEŁDZIE.contains(typ)) {
            return ofertySprzedażyRobotników[typ.ordinal()];
        } else {
            return 0;
        }
    }

    public int podajLiczbęOfertSprzedaży(TypyProduktów typ) {
        if (PRODUKTY_NA_GIEŁDZIE.contains(typ)) {
            int id = typ.ordinal();
            return ofertySprzedażyRobotników[id] + ofertySprzedażySpekulantów[id];
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "PodsumowanieDnia{" +
                "średnie=" + średnie +
                ", cenyZerowe=" + cenyZerowe +
                ", najniższeCeny=" + najniższeCeny +
                ", najwyższeCeny=" + najwyższeCeny +
                ", ofertySprzedażySpekulantów=" + Arrays.toString(ofertySprzedażySpekulantów) +
                ", ofertySprzedażyRobotników=" + Arrays.toString(ofertySprzedażyRobotników) +
                '}';
    }
}
