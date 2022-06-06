package symulacja.agenci.robotnicy;

import symulacja.Symulacja;
import symulacja.agenci.Agent;
import symulacja.agenci.robotnicy.scieżkiKariery.ŚcieżkaKariery;
import symulacja.agenci.robotnicy.strategieKariery.StrategiaKariery;
import symulacja.agenci.robotnicy.strategieKupna.StrategiaKupna;
import symulacja.agenci.robotnicy.strategiePracy.StrategiaPracy;
import symulacja.agenci.robotnicy.strategieProdukcji.StrategiaProdukcji;
import symulacja.giełda.Giełda;
import symulacja.utils.PodsumowanieDnia;

public abstract class Robotnik extends Agent {
    protected ŚcieżkaKariery[] ścieżki;
    protected ŚcieżkaKariery obecnaŚcieżka;
    protected StrategiaKariery strategiaKariery;
    protected StrategiaKupna strategiaKupna;
    protected StrategiaPracy strategiaPracy;
    protected StrategiaProdukcji strategiaProdukcji;
    protected int[] produktywność;
    private int licznikGłodu;

    public abstract int podajProduktywność(Symulacja.TypyProduktów produkt);

    public abstract int ileUbrańJutro();
    public abstract int ileProgramówBrakuje();

    // Życie
    public abstract void pracuj();
    public void uczSię() {
        if (strategiaKariery.czyZmienia()) {
            int idNowejKariery = Symulacja.ID_KARIERY.get(strategiaKariery.podajNowyZawód());
            obecnaŚcieżka = ścieżki[idNowejKariery];
        } else {
            obecnaŚcieżka.dodajPoziom();
        }
    }

    public boolean przeżyjDzień() {
        if (licznikGłodu == 3) {
            return false;
        }

        if (strategiaPracy.czyPracuje()) {
            pracuj();
        } else {
            uczSię();
        }

        return true;
    }
}
