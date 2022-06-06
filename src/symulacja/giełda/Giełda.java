package symulacja.giełda;

import symulacja.giełda.oferty.OfertaRobotnika;
import symulacja.utils.PodsumowanieDnia;

public abstract class Giełda {
    public abstract void dodajOfertęKupnaRobotnika(OfertaRobotnika ofertaKupna);
    public abstract void dodajOfertęSprzedażyRobotnika(OfertaRobotnika ofertaKupna);

    public abstract int podajDzień();
    public abstract PodsumowanieDnia[] podajHistorięOstatnichDni(int ileDni);
}
