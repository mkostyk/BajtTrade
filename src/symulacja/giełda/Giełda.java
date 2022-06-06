package symulacja.giełda;

import symulacja.Symulacja;
import symulacja.giełda.oferty.Oferta;
import symulacja.giełda.oferty.OfertaRobotnika;
import symulacja.giełda.oferty.OfertaSpekulanta;
import symulacja.utils.PodsumowanieDnia;

import java.util.ArrayList;
import java.util.Arrays;

import static symulacja.Symulacja.ILE_PRODUKTÓW;

public abstract class Giełda {
    private ArrayList <OfertaRobotnika> ofertyKupnaRobotników;
    private ArrayList <OfertaRobotnika> ofertySprzedażyRobotników;

    private ArrayList <OfertaSpekulanta> ofertyKupnaSpekulantów;
    private ArrayList <OfertaSpekulanta> ofertySprzedażySpekulantów;
    private ArrayList <PodsumowanieDnia> historia;

    private int dzień;
    private int[] ileOfertSprzedażySpekulantów = new int[ILE_PRODUKTÓW];
    private int[] ileOfertSprzedażyRobotników = new int[ILE_PRODUKTÓW];


    protected Giełda() {

    }

    public void dodajOfertęKupnaRobotnika(OfertaRobotnika ofertaKupna) {
        ofertyKupnaRobotników.add(ofertaKupna);
    }

    public void dodajOfertęSprzedażyRobotnika(OfertaRobotnika ofertaSprzedaży) {
        ofertySprzedażyRobotników.add(ofertaSprzedaży);
        ileOfertSprzedażyRobotników[ofertaSprzedaży.typID()] += ofertaSprzedaży.ile();
    }

    public void dodajOfertęKupnaSpekulanta(OfertaSpekulanta ofertaKupna) {
        ofertyKupnaSpekulantów.add(ofertaKupna);
    }

    public void dodajOfertęSprzedażySpekulanta(OfertaSpekulanta ofertaSprzedaży) {
        ofertySprzedażySpekulantów.add(ofertaSprzedaży);
        ileOfertSprzedażySpekulantów[ofertaSprzedaży.typID()] += ofertaSprzedaży.ile();
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

    public void podsumujDzień(ArrayList <OfertaSpekulanta> dokonaneSprzedaże) {
        double obrót[] = new double[ILE_PRODUKTÓW];
        double ile[] = new double[ILE_PRODUKTÓW];
        double średnie[] = new double[ILE_PRODUKTÓW];

        for (OfertaSpekulanta oferta: dokonaneSprzedaże) {
            obrót[oferta.typID()] += oferta.ile() * oferta.cena();
            ile[oferta.typID()] += oferta.ile();
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
    }
}
