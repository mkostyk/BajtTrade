package main.symulacja.agenci.robotnicy;

import main.symulacja.Symulacja;
import main.symulacja.agenci.Agent;
import main.symulacja.agenci.robotnicy.scieżkiKariery.ŚcieżkaKariery;
import main.symulacja.giełda.Giełda;
import main.symulacja.strategieRobotników.strategieKariery.StrategiaKariery;
import main.symulacja.strategieRobotników.strategieKupna.StrategiaKupna;
import main.symulacja.strategieRobotników.strategiePracy.StrategiaPracy;
import main.symulacja.strategieRobotników.strategieProdukcji.StrategiaProdukcji;
import main.symulacja.fabryka.Fabryka;
import main.symulacja.giełda.oferty.OfertaRobotnika;
import main.symulacja.produkty.Produkt;

import java.util.Locale;

import static main.symulacja.Symulacja.ILE_PRODUKTÓW;
import static main.symulacja.Symulacja.ILE_ZAWODÓW;
import static main.symulacja.Symulacja.TypyProduktów.*;

// TODO
public class Robotnik extends Agent {
    private ŚcieżkaKariery[] ścieżki;
    private ŚcieżkaKariery obecnaŚcieżka;
    private StrategiaKariery strategiaKariery;
    private StrategiaKupna strategiaKupna;
    private StrategiaPracy strategiaPracy;
    private StrategiaProdukcji strategiaProdukcji;
    private int[] produktywność;

    private int licznikGłodu;

    public Robotnik (Symulacja.Zawody kariera, StrategiaKariery strategiaKariery,
                        StrategiaKupna strategiaKupna, StrategiaPracy strategiaPracy,
                        StrategiaProdukcji strategiaProdukcji, int idRobotnika, Giełda giełda) {
        this.ścieżki = new ŚcieżkaKariery[ILE_ZAWODÓW];
        for (Symulacja.Zawody zawód: Symulacja.Zawody.values()) {
            int id = Symulacja.ID_KARIERY.get(zawód);
            // TODO - temp fix
            this.ścieżki[id] = Fabryka.stwórzŚcieżkęKariery(zawód.toString().toLowerCase(Locale.ROOT));

            if (kariera == zawód) {
                this.obecnaŚcieżka = this.ścieżki[id];
            }
        }

        this.strategiaKariery = strategiaKariery;
        this.strategiaKupna = strategiaKupna;
        this.strategiaPracy = strategiaPracy;
        this.strategiaProdukcji = strategiaProdukcji;
        this.produktywność = new int[ILE_PRODUKTÓW];
        this.licznikGłodu = 0;
        this.agentID = idRobotnika;
        this.giełda = giełda;

        this.strategiaKariery.ustawRobotnika(this);
        this.strategiaKupna.ustawRobotnika(this);
        this.strategiaPracy.ustawRobotnika(this);
        this.strategiaProdukcji.ustawRobotnika(this);
    }

    public int podajProduktywność(Symulacja.TypyProduktów produkt) {
        int id = Symulacja.ID_PRODUKTU.get(produkt);
        return produktywność[id];
    }

    // TODO
    public int ileUbrańJutro() {
        return 0;
    };

    // TODO
    public int ileProgramówBrakuje() {
        return 0;
    };

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
        for (Produkt produkt: produkty.keySet()) {
            if (produkt.podajTyp() == Symulacja.TypyProduktów.NARZĘDZIA) {
                bonus += produkty.get(produkt) * produkt.podajPoziom();
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
        strategiaProdukcji.wyprodukuj();
        strategiaKupna.dokonajZakupów();
    }

    public void uczSię() {
        if (strategiaKariery.czyZmienia()) {
            int idNowejKariery = Symulacja.ID_KARIERY.get(strategiaKariery.podajNowyZawód());
            obecnaŚcieżka = ścieżki[idNowejKariery];
        } else {
            int idObecnejKariery = Symulacja.ID_KARIERY.get(obecnaŚcieżka.podajZawód());
            obecnaŚcieżka.dodajPoziom();
            ścieżki[idObecnejKariery] = obecnaŚcieżka;
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

    // TODO
    /*public void zużyjUbrania() {
        int licznikZużytych = 0;
        while (licznikZużytych < 100) {
            Itera
        }
    }*/

    // TODO (+głodowanie)
    public void zużyjPrzedmioty() {
        // JEDZENIE
        if (!zużyjProdukty(100, new Produkt(JEDZENIE, 1))) {
            licznikGłodu++;
        }



    };

    @Override
    public String toString() {
        return "Robotnik{" +
                "idRobotnika=" + agentID +
                '}';
    }
}
