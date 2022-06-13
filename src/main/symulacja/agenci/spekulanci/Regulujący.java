package main.symulacja.agenci.spekulanci;

import main.symulacja.giełda.Giełda;
import main.symulacja.produkty.Produkt;

import java.util.Map;

public class Regulujący extends Spekulant {

    public Regulujący(int id, Map<String, Double> zasoby) {
        super(id, zasoby);
    }

    @Override
    protected void wystawOfertęProduktu(Produkt produkt) {
        if (podajGiełdę().podajDzień() == 1) {
            return;
        }

        Giełda giełda = this.podajGiełdę();
        double dzisiaj = giełda.podajObecnąLiczbęOfertSprzedażyRobotników(produkt.podajTyp());
        double wczoraj = giełda.podajHistorięOstatnichDni(1)[0].podajLiczbęOfertSprzedażyRobotników(produkt.podajTyp());

        double mnożnik = dzisiaj / Math.max(wczoraj, 1);
        double cenaBazowa = giełda.podajŚredniąCenęProduktu(1, produkt) * mnożnik;

        this.dodajOfertęKupna(produkt, cenaBazowa, 0.1);
        this.dodajOfertęSprzedaży(produkt, cenaBazowa, 0.1);
    }

    @Override
    public String toString() {
        return "regulujacy_rynek";
    }
}
