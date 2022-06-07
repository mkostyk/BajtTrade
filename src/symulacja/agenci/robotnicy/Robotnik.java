package symulacja.agenci.robotnicy;

import symulacja.Symulacja;
import symulacja.agenci.Agent;
import symulacja.agenci.robotnicy.scieżkiKariery.ŚcieżkaKariery;
import symulacja.agenci.robotnicy.strategieKariery.StrategiaKariery;
import symulacja.agenci.robotnicy.strategieKupna.StrategiaKupna;
import symulacja.agenci.robotnicy.strategiePracy.StrategiaPracy;
import symulacja.agenci.robotnicy.strategieProdukcji.StrategiaProdukcji;
import symulacja.giełda.Giełda;
import symulacja.giełda.oferty.OfertaRobotnika;
import symulacja.produkty.Produkt;
import symulacja.utils.PodsumowanieDnia;

import java.util.ArrayList;

import static symulacja.Symulacja.ILE_ZAWODÓW;

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

    public int[] podajPoziomyŚcieżek() {
        int[] wynik = new int[ILE_ZAWODÓW];
        for (Symulacja.Zawody zawód: Symulacja.Zawody.values()) {
            int id = Symulacja.ID_KARIERY.get(zawód);
            wynik[id] = ścieżki[id].podajPoziom();
        }
        return wynik;
    }

    private int podajBonusZNarzędzi() {
        int bonus = 0;
        for (Produkt produkt: produkty) {
            if (produkt.podajTyp() == Symulacja.TypyProduktów.NARZĘDZIA) {
                bonus += produkt.podajIle() * produkt.podajPoziom();
            }
        }

        return bonus;
    }

    private int podajBonusZGłodu() {
        return switch (licznikGłodu) {
            case 1 -> -100;
            case 2 -> -300;
            default -> 0;
        };
    }

    private void policzProduktywność() {
        for (Symulacja.TypyProduktów typ: Symulacja.TypyProduktów.values()) {
            int id = Symulacja.ID_PRODUKTU.get(typ);
            produktywność[id] = 100 + obecnaŚcieżka.podajBonus(typ) + podajBonusZNarzędzi() + podajBonusZGłodu();
        }
    }

    // Życie
    public void pracuj() {
        // TODO - poziomy zmienione przez programy komputerowe
        policzProduktywność();
        Produkt noweProdukty = strategiaProdukcji.wyprodukuj();

        // Wystawienie na giełdzie
        if (noweProdukty.podajTyp() == Symulacja.TypyProduktów.DIAMENTY) {
            diamenty += noweProdukty.podajIle();
        } else {
            giełda.dodajOfertęSprzedażyRobotnika(new OfertaRobotnika(noweProdukty, this));
        }

        strategiaKupna.dokonajZakupów();
    }

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

    public abstract void zużyjPrzedmioty();
}
