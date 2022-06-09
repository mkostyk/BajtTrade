package main.symulacja.agenci.spekulanci;

import main.symulacja.Symulacja;
import main.symulacja.giełda.Giełda;

public class Regulujący extends Spekulant {
    public Regulujący(Giełda giełda) {
        super(giełda);
    }

    @Override
    public void wystawOferty() {
        if (this.podajDzień() == 1) {
            return;
        }

        for (int poziom = 1; poziom <= giełda.podajMaksymalnyPoziom(); poziom++) {
            for (Symulacja.TypyProduktów typ: Symulacja.TypyProduktów.values()) {
                Giełda giełda = this.podajGiełdę();
                double dzisiaj = giełda.podajObecnąLiczbęOfertSprzedażyRobotników(typ);
                double wczoraj = giełda.podajHistorięOstatnichDni(1)[0].podajLiczbęOfertSprzedażyRobotników(typ);

                double mnożnik = dzisiaj / Math.max(wczoraj, 1);
                double cenaBazowa = giełda.podajŚredniąCenęProduktu(1, typ, poziom) * mnożnik;

                this.dodajOfertęKupna(typ, poziom, cenaBazowa, 0.1);
                this.dodajOfertęSprzedaży(typ, poziom, cenaBazowa, 0.1);
            }
        }

    }
}
