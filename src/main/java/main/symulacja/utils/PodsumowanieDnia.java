package main.symulacja.utils;

import main.symulacja.produkty.Produkt;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import static main.Main.*;

public class PodsumowanieDnia {
    private final Map<Produkt, Double> obrót;
    private final Map<Produkt, Double> ile;
    private final Map<Produkt, Double> cenyZerowe;
    private final Map<Produkt, Double> najniższeCeny;
    private final Map<Produkt, Double> najwyższeCeny;
    private final Map<TypyProduktów, Double> średnieTypów;

    private final int[] ofertySprzedażySpekulantów;
    private final int[] ofertySprzedażyRobotników;

    public PodsumowanieDnia(Map<Produkt, Double> obrót, Map<Produkt, Double> ile, Map<Produkt, Double> cenyZerowe,
                            int[] ofertySprzedażySpekulantów, int[] ofertySprzedażyRobotników,
                            Map<Produkt, Double> najniższeCeny, Map<Produkt, Double> najwyższeCeny) {
        this.obrót = obrót;
        this.ile = ile;
        this.cenyZerowe = cenyZerowe;
        this.ofertySprzedażySpekulantów = ofertySprzedażySpekulantów;
        this.ofertySprzedażyRobotników = ofertySprzedażyRobotników;
        this.najniższeCeny = najniższeCeny;
        this.najwyższeCeny = najwyższeCeny;
        this.średnieTypów = new TreeMap<>();
        policzŚrednieCenyTypów();
    }

    private void policzŚrednieCenyTypów() {
        Map <TypyProduktów, Double> obrótTypu = new TreeMap<>();
        Map <TypyProduktów, Double> ileTypu = new TreeMap<>();

        for (Produkt produkt: ile.keySet()) {
            obrótTypu.merge(produkt.podajTyp(), obrót.get(produkt), Double::sum);
            ileTypu.merge(produkt.podajTyp(), ile.get(produkt), Double::sum);
        }

        for (TypyProduktów typ: PRODUKTY_NA_GIEŁDZIE) {
            if (ileTypu.get(typ) == null || ileTypu.get(typ) == 0) {
                średnieTypów.put(typ, cenyZerowe.get(new Produkt(typ, 1)));
            } else {
                średnieTypów.put(typ, obrótTypu.get(typ) / ileTypu.get(typ));
            }
        }
    }

    private double podajCenęZerową(Produkt produkt) {
        return cenyZerowe.get(new Produkt(produkt.podajTyp(), 1));
    }

    public double podajŚredniąCenę(Produkt produkt) {
        if (PRODUKTY_NA_GIEŁDZIE.contains(produkt.podajTyp())) {
            if (ile.get(produkt) == null || ile.get(produkt) == 0) {
                return podajCenęZerową(produkt);
            } else {
                return obrót.get(produkt) / ile.get(produkt);
            }
        } else {
            return 0;
        }
    }

    public double podajNajniższąCenę(Produkt produkt) {
        if (PRODUKTY_NA_GIEŁDZIE.contains(produkt.podajTyp())) {
            najniższeCeny.putIfAbsent(produkt, podajCenęZerową(produkt));
            return najniższeCeny.get(produkt);
        } else {
            return 0;
        }
    }

    public double podajŚredniąCenęTypu(TypyProduktów typ) {
        if (PRODUKTY_NA_GIEŁDZIE.contains(typ)) {
            return średnieTypów.get(typ);
        } else {
            return 0;
        }
    }

    public double podajNajniższąCenęTypu(TypyProduktów typ) {
        double najniższa = INFINITY;
        for (Produkt produkt: najniższeCeny.keySet()) {
            if (produkt.podajTyp() == typ && ile.get(produkt) != null && ile.get(produkt) != 0) {
                najniższa = Math.min(najniższa, najniższeCeny.get(produkt));
            }
        }

        if (najniższa == INFINITY) {
            return cenyZerowe.get(new Produkt(typ, 1));
        } else {
            return najniższa;
        }
    }

    public double podajNajwyższąCenęTypu(TypyProduktów typ) {
        double najwyższa = -INFINITY;
        for (Produkt produkt: najwyższeCeny.keySet()) {
            if (produkt.podajTyp() == typ && ile.get(produkt) != null && ile.get(produkt) != 0) {
                najwyższa = Math.max(najwyższa, najwyższeCeny.get(produkt));
            }
        }

        if (najwyższa == -INFINITY) {
            return cenyZerowe.get(new Produkt(typ, 1));
        } else {
            return najwyższa;
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
                ", cenyZerowe=" + cenyZerowe +
                ", najniższeCeny=" + najniższeCeny +
                ", najwyższeCeny=" + najwyższeCeny +
                ", ofertySprzedażySpekulantów=" + Arrays.toString(ofertySprzedażySpekulantów) +
                ", ofertySprzedażyRobotników=" + Arrays.toString(ofertySprzedażyRobotników) +
                '}';
    }
}
