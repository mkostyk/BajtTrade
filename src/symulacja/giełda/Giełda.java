package symulacja.giełda;

import symulacja.Symulacja;
import symulacja.giełda.oferty.OfertaRobotnika;
import symulacja.giełda.oferty.OfertaSpekulanta;
import symulacja.utils.PodsumowanieDnia;

public abstract class Giełda {
    public abstract void dodajOfertęKupnaRobotnika(OfertaRobotnika ofertaKupna);
    public abstract void dodajOfertęSprzedażyRobotnika(OfertaRobotnika ofertaSprzedaży);

    public abstract void dodajOfertęKupnaSpekulanta(OfertaSpekulanta ofertaKupna);
    public abstract void dodajOfertęSprzedażySpekulanta(OfertaSpekulanta ofertaSprzedaży);

    public abstract int podajDzień();
    public abstract PodsumowanieDnia[] podajHistorięOstatnichDni(int ileDni);
    public abstract double podajŚredniąCenęProduktu(int ileDni, Symulacja.TypyProduktów produkt);
    public abstract int podajObecnąLiczbęOfertSprzedażyRobotników(Symulacja.TypyProduktów produkt);
    public abstract int podajMaksymalnyPoziomProduktu(Symulacja.TypyProduktów produkt);
}
