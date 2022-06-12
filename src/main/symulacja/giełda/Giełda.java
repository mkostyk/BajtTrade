package main.symulacja.giełda;

import com.sun.source.tree.Tree;
import main.symulacja.Symulacja;
import main.symulacja.giełda.oferty.Oferta;
import main.symulacja.giełda.oferty.OfertaRobotnika;
import main.symulacja.giełda.oferty.OfertaSpekulanta;
import main.symulacja.giełda.oferty.ZbiórOfertSpekulanta;
import main.symulacja.komparatory.KomparatorProduktów;
import main.symulacja.produkty.Produkt;
import main.symulacja.utils.PodsumowanieDnia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import static main.symulacja.Symulacja.ILE_PRODUKTÓW;
import static main.symulacja.Symulacja.INFINITY;

public abstract class Giełda {
    protected ArrayList <OfertaRobotnika> ofertyKupnaRobotników = new ArrayList<>();
    protected ArrayList <OfertaRobotnika> ofertySprzedażyRobotników = new ArrayList<>();

    private ZbiórOfertSpekulanta ofertyKupnaSpekulantów = new ZbiórOfertSpekulanta();
    private ZbiórOfertSpekulanta ofertySprzedażySpekulantów = new ZbiórOfertSpekulanta();
    private final TreeMap<Produkt, Double> cenyZerowe;
    private final ArrayList <PodsumowanieDnia> historia = new ArrayList<>();
    private ArrayList <OfertaSpekulanta> dokonaneSprzedaże = new ArrayList<>();

    private int dzień;
    private final int karaZaUbrania; // Nie chcemy, żeby robotnik miał dostęp do całej symulacji.
    private final int[] ileOfertSprzedażySpekulantów;
    private final int[] ileOfertSprzedażyRobotników;

    protected Giełda(TreeMap<Produkt, Double> cenyZerowe, int karaZaUbrania) {
        int[] sprzedażDniaZerowego = new int[ILE_PRODUKTÓW];
        Arrays.fill(sprzedażDniaZerowego, 0);
        PodsumowanieDnia p = new PodsumowanieDnia(cenyZerowe, cenyZerowe, sprzedażDniaZerowego, sprzedażDniaZerowego, cenyZerowe);

        historia.add(p);
        ileOfertSprzedażyRobotników = sprzedażDniaZerowego;
        ileOfertSprzedażySpekulantów = sprzedażDniaZerowego;
        this.karaZaUbrania = karaZaUbrania;
        this.cenyZerowe = cenyZerowe;
        this.dzień = 1;
    }

    public int podajKaręZaUbrania() {
        return karaZaUbrania;
    }

    public abstract void posortujOferty();

    public void dodajOfertęKupnaRobotnika(OfertaRobotnika ofertaKupna) {
        ofertyKupnaRobotników.add(ofertaKupna);
    }

    public void dodajOfertęSprzedażyRobotnika(OfertaRobotnika ofertaSprzedaży) {
        ofertySprzedażyRobotników.add(ofertaSprzedaży);
        ileOfertSprzedażyRobotników[ofertaSprzedaży.typID()] += ofertaSprzedaży.podajIle();
    }

    public void dodajOfertęKupnaSpekulanta(OfertaSpekulanta ofertaKupna) {
        ofertyKupnaSpekulantów.dodajOfertę(ofertaKupna);
    }

    public void dodajOfertęSprzedażySpekulanta(OfertaSpekulanta ofertaSprzedaży) {
        ofertySprzedażySpekulantów.dodajOfertę(ofertaSprzedaży);
        ileOfertSprzedażySpekulantów[ofertaSprzedaży.typID()] += ofertaSprzedaży.podajIle();
    }

    public int podajDzień() {
        return dzień;
    }
    // TODO - SUS
    public int podajMaksymalnyPoziom() {
        return dzień;
    }

    public PodsumowanieDnia[] podajHistorięOstatnichDni(int ileDni) {
        PodsumowanieDnia[] tablicaHistorii = historia.toArray(new PodsumowanieDnia[0]);
        int początkowyDzień = Math.max(tablicaHistorii.length - ileDni, 0);
        int końcowyDzień = tablicaHistorii.length - 1;

        return Arrays.copyOfRange(tablicaHistorii, początkowyDzień, końcowyDzień + 1);
    }

    private double podajNajniższąCenęProduktu(Produkt produkt) {
        return podajHistorięOstatnichDni(1)[0].podajNajniższąCenę(produkt);
    }

    public double podajŚredniąCenęProduktu(int ileDni, Symulacja.TypyProduktów typ, int poziom) {
        double suma = 0;
        PodsumowanieDnia[] dane = podajHistorięOstatnichDni(ileDni);

        for (PodsumowanieDnia dzień: dane) {
            suma += dzień.podajŚredniąCenę(typ, poziom);
        }

        return suma / (dane.length);
    }

    // TODO - ustandaryzować i używać jednej
    public double podajŚredniąCenęProduktu(int ileDni, Produkt produkt) {
        return podajŚredniąCenęProduktu(ileDni, produkt.podajTyp(), produkt.podajPoziom());
    }

    public int podajObecnąLiczbęOfertSprzedażyRobotników(Symulacja.TypyProduktów produkt) {
        return ileOfertSprzedażyRobotników[Symulacja.ID_PRODUKTU.get(produkt)];
    }

    public boolean wykonajOfertę(Oferta ofertaZakupu, Oferta ofertaSprzedaży, Produkt produkt, double cena) {
        double dostępneDiamenty = ofertaZakupu.podajTwórcę().ileDiamentów();

        int zakup = Math.min(ofertaZakupu.podajIle(), ofertaSprzedaży.podajIle());
        zakup = (int) Math.min(zakup, Math.floor(dostępneDiamenty / cena));
        zakup = (int) Math.min(zakup, ofertaSprzedaży.podajTwórcę().ileProduktów(produkt));

        double wartośćZakupu = zakup * cena;

        ofertaZakupu.podajTwórcę().zużyjDiamenty(wartośćZakupu);
        ofertaZakupu.podajTwórcę().dodajProdukty(zakup, produkt);

        ofertaSprzedaży.podajTwórcę().dodajDiamenty(wartośćZakupu);
        ofertaSprzedaży.podajTwórcę().zużyjProdukty(zakup, produkt);

        ofertaZakupu.zmniejszWielkość(zakup);
        ofertaSprzedaży.zmniejszWielkość(zakup);

        dokonaneSprzedaże.add(new OfertaSpekulanta(produkt, zakup, cena));

        return zakup > 0;
    }


    public void dopasujOferty() {
        wypisz();
        posortujOferty();
        wypisz();
        ofertyKupnaSpekulantów.posortujOferty();
        ofertySprzedażySpekulantów.posortujOferty();

        // Sprzedaż robotników
        for (OfertaRobotnika oferta: ofertySprzedażyRobotników) {
            int obecnaPozycja = ofertyKupnaSpekulantów.znajdźNajlepsząOfertęKupna(oferta);
            if (obecnaPozycja >= ofertyKupnaSpekulantów.size()) {
                continue;
            }

            // Znajdujemy najlepszą ofertę
            OfertaSpekulanta ofertaZakupu = ofertyKupnaSpekulantów.podajOfertę(obecnaPozycja);

            while (oferta.podajIle() > 0 && oferta.typID() == ofertaZakupu.typID()
                   && oferta.podajPoziom() == ofertaZakupu.podajPoziom() &&
                   obecnaPozycja < ofertyKupnaSpekulantów.size()) {
                // Wykonujemy ofertę
                if (!wykonajOfertę(ofertaZakupu, oferta, ofertaZakupu.podajProdukt(), ofertaZakupu.podajCenę())) {
                    break;
                }

                // Oferty wcześniejsze są niezgodne z obecną
                obecnaPozycja++;
                if (obecnaPozycja < ofertyKupnaSpekulantów.size()) {
                    ofertaZakupu = ofertyKupnaSpekulantów.podajOfertę(obecnaPozycja);
                }
            }
        }

        // TODO - funkcja

        // Zakupy robotników
        for (OfertaRobotnika oferta: ofertyKupnaRobotników) {
            int obecnaPozycja = ofertySprzedażySpekulantów.znajdźNajlepsząOfertęSprzedaży(oferta);
            if (obecnaPozycja >= ofertySprzedażySpekulantów.size()) {
                continue;
            }

            OfertaSpekulanta ofertaSprzedaży = ofertySprzedażySpekulantów.podajOfertę(obecnaPozycja);

            while (oferta.podajIle() > 0 && oferta.typID() == ofertaSprzedaży.typID() &&
                   obecnaPozycja < ofertySprzedażySpekulantów.size()) {
                //System.out.println("Pozycja kupna: " + obecnaPozycja);
                //System.out.println(oferta.podajTwórcę());

                if(!wykonajOfertę(oferta, ofertaSprzedaży, ofertaSprzedaży.podajProdukt(), ofertaSprzedaży.podajCenę())) {
                    break;
                }

                // Oferty wcześniejsze są niezgodne z obecną
                obecnaPozycja++;
                if (obecnaPozycja < ofertySprzedażySpekulantów.size()) {
                    ofertaSprzedaży = ofertySprzedażySpekulantów.podajOfertę(obecnaPozycja);
                }
            }
        }
    }

    public void skupOferty() {
        for (OfertaRobotnika pozostałeOferty: ofertySprzedażyRobotników) {
            double cena = podajNajniższąCenęProduktu(pozostałeOferty.podajProdukt());
            pozostałeOferty.podajTwórcę().dodajDiamenty(cena * pozostałeOferty.podajIle());
            pozostałeOferty.podajTwórcę().zużyjProdukty(pozostałeOferty.podajIle(), pozostałeOferty.podajProdukt());
        }

        ofertySprzedażyRobotników = new ArrayList<>();
    }

    public void podsumujDzień() {
        TreeMap<Produkt, Double> obrót = new TreeMap<>(new KomparatorProduktów());
        TreeMap<Produkt, Double> ile = new TreeMap<>(new KomparatorProduktów());
        TreeMap<Produkt, Double> średnie = new TreeMap<>(new KomparatorProduktów());
        TreeMap<Produkt, Double> najniższeCeny = new TreeMap<>(new KomparatorProduktów());

        for (OfertaSpekulanta oferta: dokonaneSprzedaże) {
            double wzrostObrotu = oferta.podajIle() * oferta.podajCenę();
            Produkt produkt = oferta.podajProdukt();

            obrót.putIfAbsent(produkt, 0.0);
            ile.putIfAbsent(produkt, 0.0);
            najniższeCeny.putIfAbsent(produkt, INFINITY);

            obrót.put(produkt, obrót.get(produkt) + wzrostObrotu);
            ile.put(produkt, ile.get(produkt) + oferta.podajIle());
            najniższeCeny.put(produkt, Math.min(najniższeCeny.get(produkt), oferta.podajCenę()));
        }

        for (int poziom = 1; poziom <= podajMaksymalnyPoziom(); poziom++) {
            for (Symulacja.TypyProduktów typ: Symulacja.TypyProduktów.values()) {
                Produkt produkt = new Produkt(typ, poziom);
                if (ile.get(produkt) == null) {
                    // Cena z dnia zerowego.
                    średnie.put(produkt, podajHistorięOstatnichDni(dzień)[0].podajŚredniąCenę(typ, poziom));
                } else {
                    średnie.put(produkt, obrót.get(produkt) / ile.get(produkt));
                    System.out.println(produkt + " " + obrót.get(produkt) + " " + ile.get(produkt) + " " + obrót.get(produkt) / ile.get(produkt));
                }
            }
        }


        historia.add(new PodsumowanieDnia(średnie, cenyZerowe, ileOfertSprzedażySpekulantów, ileOfertSprzedażyRobotników, najniższeCeny));

        // Czyszczenie ofert
        ofertyKupnaRobotników = new ArrayList<>();
        ofertySprzedażyRobotników = new ArrayList<>();
        ofertyKupnaSpekulantów = new ZbiórOfertSpekulanta();
        ofertySprzedażySpekulantów = new ZbiórOfertSpekulanta();
        dokonaneSprzedaże = new ArrayList<>();

        dzień++;
    }


    public void wypisz() {
        System.out.println("---------- OFERTY ROBOTNIKÓW ----------");
        System.out.println("ZAKUPU: ");
        for (Oferta oferta: ofertyKupnaRobotników) {
            System.out.println(oferta);
        }

        System.out.println();

        System.out.println("SPRZEDAŻY: ");
        for (Oferta oferta: ofertySprzedażyRobotników) {
            System.out.println(oferta);
        }

        System.out.println();

        System.out.println("---------- OFERTY SPEKULANTÓW ----------");
        System.out.println("ZAKUPU: ");
        for (Oferta oferta: ofertyKupnaSpekulantów.podajWszystkieOferty()) {
            System.out.println(oferta);
        }

        System.out.println();

        System.out.println("SPRZEDAŻY: ");
        for (Oferta oferta: ofertySprzedażySpekulantów.podajWszystkieOferty()) {
            System.out.println(oferta);
        }
    }

    public void ustawDzień(int dzień) {
        this.dzień = dzień;
    }
}
