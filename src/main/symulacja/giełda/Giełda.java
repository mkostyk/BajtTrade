package main.symulacja.giełda;

import main.symulacja.Symulacja;
import main.symulacja.agenci.Agent;
import main.symulacja.giełda.oferty.Oferta;
import main.symulacja.giełda.oferty.OfertaRobotnika;
import main.symulacja.giełda.oferty.OfertaSpekulanta;
import main.symulacja.giełda.oferty.ZbiórOfertSpekulanta;
import main.symulacja.utils.PodsumowanieDnia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static main.symulacja.Symulacja.ILE_PRODUKTÓW;

public abstract class Giełda {
    private ArrayList <OfertaRobotnika> ofertyKupnaRobotników = new ArrayList<>();
    private ArrayList <OfertaRobotnika> ofertySprzedażyRobotników = new ArrayList<>();

    private ZbiórOfertSpekulanta ofertyKupnaSpekulantów = new ZbiórOfertSpekulanta();
    private ZbiórOfertSpekulanta ofertySprzedażySpekulantów = new ZbiórOfertSpekulanta();
    private ArrayList <PodsumowanieDnia> historia;
    private ArrayList <OfertaSpekulanta> dokonaneSprzedaże;

    private int dzień;
    private int[] ileOfertSprzedażySpekulantów = new int[ILE_PRODUKTÓW];
    private int[] ileOfertSprzedażyRobotników = new int[ILE_PRODUKTÓW];

    // TODO
    protected Giełda() {

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

    public PodsumowanieDnia[] podajHistorięOstatnichDni(int ileDni) {
        PodsumowanieDnia[] tablicaHistorii = historia.toArray(new PodsumowanieDnia[0]);
        int początkowyDzień = Math.max(tablicaHistorii.length - ileDni, 0);
        int końcowyDzień = tablicaHistorii.length - 1;

        return Arrays.copyOfRange(tablicaHistorii, początkowyDzień, końcowyDzień);

    }

    public double podajŚredniąCenęProduktu(int ileDni, Symulacja.TypyProduktów produkt) {
        double suma = 0;
        PodsumowanieDnia[] dane = podajHistorięOstatnichDni(ileDni);
        for (PodsumowanieDnia dzień: dane) {
            suma += dzień.podajŚredniąCenę(produkt);
        }

        return suma/(dane.length);
    }

    public int podajObecnąLiczbęOfertSprzedażyRobotników(Symulacja.TypyProduktów produkt) {
        return ileOfertSprzedażyRobotników[Symulacja.ID_PRODUKTU.get(produkt)];
    }

    public abstract void posortujOferty();

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

            ofertaZakupu.zmniejszWielkość(zakup);
            ofertaSprzedaży.zmniejszWielkość(zakup);
        }
    }


    public void dopasujOferty() {
        posortujOferty();
        // Sprzedaż robotników
        for (OfertaRobotnika oferta: ofertySprzedażyRobotników) {
            int obecnaPozycja = ofertyKupnaSpekulantów.znajdźNajlepsząOfertęKupna(oferta);
            OfertaSpekulanta ofertaZakupu = ofertyKupnaSpekulantów.podajOfertę(obecnaPozycja);

            while (oferta.podajIle() > 0 && oferta.typID() == ofertaZakupu.typID()
                   && oferta.podajPoziom() == ofertaZakupu.podajPoziom()) {
                // Znajdujemy najlepszą ofertę
                wykonajOfertę(ofertaZakupu, oferta, ofertaZakupu.podajCenę());

                // Oferty wcześniejsze są niezgodne z obecną
                obecnaPozycja++;
                ofertaZakupu = ofertyKupnaSpekulantów.podajOfertę(obecnaPozycja);
            }
        }

        // Zakupy robotników
        for (OfertaRobotnika oferta: ofertyKupnaRobotników) {
            int obecnaPozycja = ofertySprzedażySpekulantów.znajdźNajlepsząOfertęSprzedaży(oferta);
            OfertaSpekulanta ofertaSprzedaży = ofertySprzedażySpekulantów.podajOfertę(obecnaPozycja);

            while (oferta.podajIle() > 0 && oferta.typID() == ofertaSprzedaży.typID()) {
                wykonajOfertę(ofertaSprzedaży, oferta, ofertaSprzedaży.podajCenę());

                // Oferty wcześniejsze są niezgodne z obecną
                obecnaPozycja++;
                ofertaSprzedaży = ofertySprzedażySpekulantów.podajOfertę(obecnaPozycja);
            }
        }
    }

    public void skupOferty() {
        for (OfertaRobotnika pozostałeOferty: ofertySprzedażyRobotników) {
            double cena = podajŚredniąCenęProduktu(1, pozostałeOferty.podajTyp());
            pozostałeOferty.podajTwórcę().dodajDiamenty(cena * pozostałeOferty.podajIle());
        }

        ofertySprzedażyRobotników = null;
    }

    public void podsumujDzień() {
        double[] obrót = new double[ILE_PRODUKTÓW];
        double[] ile = new double[ILE_PRODUKTÓW];
        double[] średnie = new double[ILE_PRODUKTÓW];

        for (OfertaSpekulanta oferta: dokonaneSprzedaże) {
            obrót[oferta.typID()] += oferta.podajIle() * oferta.podajCenę();
            ile[oferta.typID()] += oferta.podajIle();
        }

        for (Symulacja.TypyProduktów produkt: Symulacja.TypyProduktów.values()) {
            int id = Symulacja.ID_PRODUKTU.get(produkt);
            if (ile[id] == 0) {
                // Cena z dnia zerowego.
                średnie[id] = podajHistorięOstatnichDni(dzień)[0].podajŚredniąCenę(produkt);
            }
            średnie[id] = obrót[id] / ile[id];
        }

        historia.add(new PodsumowanieDnia(średnie, ileOfertSprzedażySpekulantów, ileOfertSprzedażyRobotników));

        // Czyszczenie ofert
        ofertyKupnaRobotników = new ArrayList<>();
        ofertySprzedażyRobotników = new ArrayList<>();
        ofertyKupnaSpekulantów = new ZbiórOfertSpekulanta();
        ofertySprzedażySpekulantów = new ZbiórOfertSpekulanta();
    }
}
