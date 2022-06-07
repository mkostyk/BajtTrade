package main.symulacja.agenci.spekulanci;

import main.symulacja.Symulacja;
import main.symulacja.giełda.Giełda;

public class Regulujący extends Spekulant {
    @Override
    public void wystawOferty() {
        if (this.podajDzień() == 1) {
            return;
        }

        for (Symulacja.TypyProduktów produkt: Symulacja.TypyProduktów.values()) {
            Giełda giełda = this.podajGiełdę();
            double dzisiaj = giełda.podajObecnąLiczbęOfertSprzedażyRobotników(produkt);
            double wczoraj = giełda.podajHistorięOstatnichDni(1)[0].podajLiczbęOfertSprzedażyRobotników(produkt);

            double mnożnik = dzisiaj / Math.max(wczoraj, 1);
            double cenaBazowa = giełda.podajŚredniąCenęProduktu(1, produkt) * mnożnik;

            this.dodajOfertęKupna(produkt, cenaBazowa, 0.1);
            this.dodajOfertęSprzedaży(produkt, cenaBazowa, 0.1);
        }
    }
}
