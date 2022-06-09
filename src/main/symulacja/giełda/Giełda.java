package main.symulacja.giełda;

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
import java.util.Map;
import java.util.TreeMap;

import static main.symulacja.Symulacja.ILE_PRODUKTÓW;

// TODO - powinien być abstract
public class Giełda {
    protected ArrayList <OfertaRobotnika> ofertyKupnaRobotników = new ArrayList<>();
    protected ArrayList <OfertaRobotnika> ofertySprzedażyRobotników = new ArrayList<>();

    private ZbiórOfertSpekulanta ofertyKupnaSpekulantów = new ZbiórOfertSpekulanta();
    private ZbiórOfertSpekulanta ofertySprzedażySpekulantów = new ZbiórOfertSpekulanta();
    private ArrayList <PodsumowanieDnia> historia = new ArrayList<>();
    private ArrayList <OfertaSpekulanta> dokonaneSprzedaże = new ArrayList<>();

    private int dzień;
    private int[] ileOfertSprzedażySpekulantów = new int[ILE_PRODUKTÓW];
    private int[] ileOfertSprzedażyRobotników = new int[ILE_PRODUKTÓW];

    // TODO - konstruktor protected
    protected Giełda(TreeMap<Produkt, Double> cenyZerowe) {
        int[] sprzedażDniaZerowego = new int[ILE_PRODUKTÓW];
        Arrays.fill(sprzedażDniaZerowego, 0);
        PodsumowanieDnia p = new PodsumowanieDnia(cenyZerowe, sprzedażDniaZerowego, sprzedażDniaZerowego);
        historia.add(p);
        ileOfertSprzedażyRobotników = sprzedażDniaZerowego;
        ileOfertSprzedażySpekulantów = sprzedażDniaZerowego;
    }

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

        // TODO - testing
        //System.out.println(początkowyDzień + " " + końcowyDzień);

        //PodsumowanieDnia[] test = Arrays.copyOfRange(tablicaHistorii, początkowyDzień, końcowyDzień);
        //System.out.println(Arrays.toString(tablicaHistorii));

        return Arrays.copyOfRange(tablicaHistorii, początkowyDzień, końcowyDzień + 1);
    }

    public double podajŚredniąCenęProduktu(int ileDni, Symulacja.TypyProduktów typ, int poziom) {
        double suma = 0;
        PodsumowanieDnia[] dane = podajHistorięOstatnichDni(ileDni);
        // TODO - testing
        //System.out.println(Arrays.toString(dane));
        for (PodsumowanieDnia dzień: dane) {
            suma += dzień.podajŚredniąCenę(typ, poziom);
        }

        return suma / (dane.length);
    }

    public int podajObecnąLiczbęOfertSprzedażyRobotników(Symulacja.TypyProduktów produkt) {
        return ileOfertSprzedażyRobotników[Symulacja.ID_PRODUKTU.get(produkt)];
    }

    // TODO
    public void posortujOferty() {

    }

    public void wykonajOfertę(Oferta ofertaZakupu, Oferta ofertaSprzedaży, double cena) {
        // TODO - przerwać kupowanie w razie braku diamentów
        double dostępneDiamenty = ofertaZakupu.podajTwórcę().ileDiamentów();
        int zakup = Math.min(ofertaZakupu.podajIle(), ofertaSprzedaży.podajIle());
        zakup = (int) Math.min(zakup, Math.floor(dostępneDiamenty / cena));

        double wartośćZakupu = zakup * cena;

        // TODO - ten if powinien zawsze się wykonać
        if (ofertaZakupu.podajTwórcę().ileDiamentów() >= wartośćZakupu && zakup > 0) {
            ofertaZakupu.podajTwórcę().dodajDiamenty(-wartośćZakupu);
            ofertaSprzedaży.podajTwórcę().dodajDiamenty(wartośćZakupu);
            // TODO - testing
            //System.out.println(ofertaZakupu.podajTwórcę().ileDiamentów());
            //System.out.println(ofertaSprzedaży.podajTwórcę().ileDiamentów());

            ofertaZakupu.zmniejszWielkość(zakup);
            ofertaSprzedaży.zmniejszWielkość(zakup);
        }
    }


    public void dopasujOferty() {
        posortujOferty();
        // Sprzedaż robotników
        for (OfertaRobotnika oferta: ofertySprzedażyRobotników) {
            int obecnaPozycja = ofertyKupnaSpekulantów.znajdźNajlepsząOfertęKupna(oferta);
            //System.out.println("POZYCJA1: " + obecnaPozycja);
            if (obecnaPozycja >= ofertyKupnaSpekulantów.size()) {
                continue;
            }
            OfertaSpekulanta ofertaZakupu = ofertyKupnaSpekulantów.podajOfertę(obecnaPozycja);

            while (oferta.podajIle() > 0 && oferta.typID() == ofertaZakupu.typID()
                   && oferta.podajPoziom() == ofertaZakupu.podajPoziom() &&
                   obecnaPozycja < ofertyKupnaSpekulantów.size()) {
                // Znajdujemy najlepszą ofertę
                wykonajOfertę(ofertaZakupu, oferta, ofertaZakupu.podajCenę());

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
            //System.out.println("POZYCJA2: " + obecnaPozycja);
            if (obecnaPozycja >= ofertySprzedażySpekulantów.size()) {
                continue;
            }

            OfertaSpekulanta ofertaSprzedaży = ofertySprzedażySpekulantów.podajOfertę(obecnaPozycja);

            while (oferta.podajIle() > 0 && oferta.typID() == ofertaSprzedaży.typID() &&
                    obecnaPozycja < ofertySprzedażySpekulantów.size()) {
                wykonajOfertę(oferta, ofertaSprzedaży, ofertaSprzedaży.podajCenę());

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
            double cena = podajŚredniąCenęProduktu(1, pozostałeOferty.podajTyp(), pozostałeOferty.podajPoziom());
            pozostałeOferty.podajTwórcę().dodajDiamenty(cena * pozostałeOferty.podajIle());
        }

        ofertySprzedażyRobotników = new ArrayList<>();
    }

    public void podsumujDzień() {
        TreeMap<Produkt, Double> obrót = new TreeMap<>(new KomparatorProduktów());
        TreeMap<Produkt, Double> ile = new TreeMap<>(new KomparatorProduktów());
        TreeMap<Produkt, Double> średnie = new TreeMap<>(new KomparatorProduktów());

        for (OfertaSpekulanta oferta: dokonaneSprzedaże) {
            double wzrostObrotu = oferta.podajIle() * oferta.podajCenę();
            Produkt produkt = oferta.podajProdukt();

            obrót.put(produkt, obrót.get(produkt) + wzrostObrotu);
            ile.put(produkt, ile.get(produkt) + oferta.podajIle());
        }

        for (int poziom = 1; poziom <= podajMaksymalnyPoziom(); poziom++) {
            for (Symulacja.TypyProduktów typ: Symulacja.TypyProduktów.values()) {
                Produkt produkt = new Produkt(typ, poziom);
                if (ile.get(produkt) == 0) {
                    // Cena z dnia zerowego.
                    średnie.put(produkt, podajHistorięOstatnichDni(dzień)[0].podajŚredniąCenę(typ, poziom));
                }
                średnie.put(produkt, obrót.get(produkt) / ile.get(produkt));
            }
        }


        historia.add(new PodsumowanieDnia(średnie, ileOfertSprzedażySpekulantów, ileOfertSprzedażyRobotników));

        // Czyszczenie ofert
        ofertyKupnaRobotników = new ArrayList<>();
        ofertySprzedażyRobotników = new ArrayList<>();
        ofertyKupnaSpekulantów = new ZbiórOfertSpekulanta();
        ofertySprzedażySpekulantów = new ZbiórOfertSpekulanta();
    }

    // TODO - testing
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

    // TODO - testing

    public void ustawDzień(int dzień) {
        this.dzień = dzień;
    }
}
