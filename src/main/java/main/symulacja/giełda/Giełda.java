package main.symulacja.giełda;

import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.giełda.oferty.Oferta;
import main.symulacja.giełda.oferty.OfertaRobotnika;
import main.symulacja.giełda.oferty.OfertaSpekulanta;
import main.symulacja.giełda.oferty.ZbiórOfertSpekulanta;
import main.symulacja.komparatory.KomparatorOfertKupnaSpekulantów;
import main.symulacja.komparatory.KomparatorOfertSprzedażySpekulantów;
import main.symulacja.komparatory.KomparatorProduktów;
import main.symulacja.produkty.Produkt;
import main.symulacja.utils.PodsumowanieDnia;

import java.util.*;

import static java.util.Map.entry;
import static main.Main.ILE_PRODUKTÓW;
import static main.Main.INFINITY;
import static main.Main.TypyProduktów;
import static main.Main.TypyProduktów.*;

public abstract class Giełda {
    protected List <OfertaRobotnika> ofertyKupnaRobotników = new ArrayList<>();
    protected List <OfertaRobotnika> ofertySprzedażyRobotników = new ArrayList<>();

    private ZbiórOfertSpekulanta ofertyKupnaSpekulantów = new ZbiórOfertSpekulanta();
    private ZbiórOfertSpekulanta ofertySprzedażySpekulantów = new ZbiórOfertSpekulanta();
    private final Map<Produkt, Double> cenyZerowe;
    private final List <PodsumowanieDnia> historia = new ArrayList<>();
    private List <OfertaSpekulanta> dokonaneSprzedaże = new ArrayList<>();

    private int dzień;
    private final int karaZaUbrania; // Nie chcemy, żeby robotnik miał dostęp do całej symulacji.
    private final int[] ileOfertSprzedażySpekulantów;
    private final int[] ileOfertSprzedażyRobotników;

    protected Giełda(Map<Produkt, Double> cenyZerowe, int karaZaUbrania) {
        int[] ofertySprzedażySpekulantówDniaZerowego  = new int[ILE_PRODUKTÓW];
        int[] ofertySprzedażyRobotnikówDniaZerowego = new int[ILE_PRODUKTÓW];
        Map<Produkt, Double> sprzedane = Map.ofEntries(
                entry(new Produkt(JEDZENIE,1), 1.0),
                entry(new Produkt(UBRANIA,1), 1.0),
                entry(new Produkt(NARZEDZIA,1), 1.0),
                entry(new Produkt(PROGRAMY,1), 1.0));

        Arrays.fill(ofertySprzedażySpekulantówDniaZerowego, 1);
        Arrays.fill(ofertySprzedażyRobotnikówDniaZerowego, 0);

        PodsumowanieDnia p = new PodsumowanieDnia(cenyZerowe, sprzedane, cenyZerowe,
                                                  ofertySprzedażySpekulantówDniaZerowego,
                                                  ofertySprzedażyRobotnikówDniaZerowego, cenyZerowe, cenyZerowe);

        historia.add(p);
        ileOfertSprzedażyRobotników = ofertySprzedażyRobotnikówDniaZerowego;
        ileOfertSprzedażySpekulantów = ofertySprzedażySpekulantówDniaZerowego;
        this.karaZaUbrania = karaZaUbrania;
        this.cenyZerowe = cenyZerowe;
        this.dzień = 1;
    }

    public int podajKaręZaUbrania() {
        return karaZaUbrania;
    }

    protected abstract void posortujOferty();

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

    public double podajŚredniąCenęProduktu(int ileDni, Produkt produkt) {
        double suma = 0;
        PodsumowanieDnia[] dane = podajHistorięOstatnichDni(ileDni);

        for (PodsumowanieDnia dzień: dane) {
            suma += dzień.podajŚredniąCenę(produkt);
        }

        return suma / (dane.length);
    }

    public int podajObecnąLiczbęOfertSprzedażyRobotników(TypyProduktów typ) {
        return ileOfertSprzedażyRobotników[typ.ordinal()];
    }

    private void wykonajOfertę(Oferta ofertaZakupu, Oferta ofertaSprzedaży, Produkt produkt, double cena) {
        double dostępneDiamenty = ofertaZakupu.podajTwórcę().ileDiamentów();

        int zakup = Math.min(ofertaZakupu.podajIle(), ofertaSprzedaży.podajIle());
        zakup = (int) Math.min(zakup, Math.floor(dostępneDiamenty / cena));

        double wartośćZakupu = zakup * cena;

        ofertaZakupu.podajTwórcę().zużyjDiamenty(wartośćZakupu);
        ofertaZakupu.podajTwórcę().dodajProdukty(zakup, produkt);

        ofertaSprzedaży.podajTwórcę().dodajDiamenty(wartośćZakupu);
        if (ofertaSprzedaży.podajTwórcę() instanceof Spekulant) {
            ofertaSprzedaży.podajTwórcę().zużyjProdukty(zakup, produkt); // TODO
        }

        ofertaZakupu.zmniejszWielkość(zakup);
        ofertaSprzedaży.zmniejszWielkość(zakup);

        if (zakup > 0) {
            dokonaneSprzedaże.add(new OfertaSpekulanta(produkt, zakup, cena));
        }
    }


    public void dopasujOferty() {
        posortujOferty();
        ofertyKupnaSpekulantów.posortujOferty(new KomparatorOfertKupnaSpekulantów());
        ofertySprzedażySpekulantów.posortujOferty(new KomparatorOfertSprzedażySpekulantów());

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
                wykonajOfertę(ofertaZakupu, oferta, ofertaZakupu.podajProdukt(), ofertaZakupu.podajCenę());

                // Oferty wcześniejsze są niezgodne z obecną
                obecnaPozycja++;
                if (obecnaPozycja < ofertyKupnaSpekulantów.size()) {
                    ofertaZakupu = ofertyKupnaSpekulantów.podajOfertę(obecnaPozycja);
                }
            }
        }

        // Zakupy robotników
        for (OfertaRobotnika oferta: ofertyKupnaRobotników) {
            int obecnaPozycja = ofertySprzedażySpekulantów.znajdźNajlepsząOfertęSprzedaży(oferta);
            if (obecnaPozycja >= ofertySprzedażySpekulantów.size()) {
                continue;
            }

            OfertaSpekulanta ofertaSprzedaży = ofertySprzedażySpekulantów.podajOfertę(obecnaPozycja);

            while (oferta.podajIle() > 0 && oferta.typID() == ofertaSprzedaży.typID() &&
                   obecnaPozycja < ofertySprzedażySpekulantów.size()) {

                wykonajOfertę(oferta, ofertaSprzedaży, ofertaSprzedaży.podajProdukt(), ofertaSprzedaży.podajCenę());

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
        }

        ofertySprzedażyRobotników = new ArrayList<>();
    }

    public void podsumujDzień() {
        Map<Produkt, Double> obrót = new TreeMap<>(new KomparatorProduktów());
        Map<Produkt, Double> ile = new TreeMap<>(new KomparatorProduktów());
        Map<Produkt, Double> najniższeCeny = new TreeMap<>(new KomparatorProduktów());
        Map<Produkt, Double> najwyższeCeny = new TreeMap<>(new KomparatorProduktów());

        for (OfertaSpekulanta oferta: dokonaneSprzedaże) {
            double wzrostObrotu = oferta.podajIle() * oferta.podajCenę();
            Produkt produkt = oferta.podajProdukt();

            obrót.putIfAbsent(produkt, 0.0);
            ile.putIfAbsent(produkt, 0.0);
            najniższeCeny.putIfAbsent(produkt, INFINITY);
            najwyższeCeny.putIfAbsent(produkt, 0.0);

            obrót.put(produkt, obrót.get(produkt) + wzrostObrotu);
            ile.put(produkt, ile.get(produkt) + oferta.podajIle());
            najniższeCeny.put(produkt, Math.min(najniższeCeny.get(produkt), oferta.podajCenę()));
            najwyższeCeny.put(produkt, Math.max(najwyższeCeny.get(produkt), oferta.podajCenę()));
        }

        historia.add(new PodsumowanieDnia(obrót, ile, cenyZerowe, ileOfertSprzedażySpekulantów,
                                          ileOfertSprzedażyRobotników, najniższeCeny, najwyższeCeny));

        // Czyszczenie ofert
        ofertyKupnaRobotników = new ArrayList<>();
        ofertySprzedażyRobotników = new ArrayList<>();
        ofertyKupnaSpekulantów = new ZbiórOfertSpekulanta();
        ofertySprzedażySpekulantów = new ZbiórOfertSpekulanta();
        dokonaneSprzedaże = new ArrayList<>();
        Arrays.fill(ileOfertSprzedażyRobotników, 0);
        Arrays.fill(ileOfertSprzedażySpekulantów, 0);

        dzień++;
    }


    @SuppressWarnings("unused")
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
}
