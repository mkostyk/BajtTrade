package main.symulacja.agenci.robotnicy;

import main.symulacja.Symulacja;
import main.symulacja.agenci.Agent;
import main.symulacja.agenci.robotnicy.scieżkiKariery.ŚcieżkaKariery;
import main.symulacja.giełda.Giełda;
import main.symulacja.komparatory.KomparatorProduktów;
import main.symulacja.strategieRobotników.strategieKariery.StrategiaKariery;
import main.symulacja.strategieRobotników.strategieKupna.StrategiaKupna;
import main.symulacja.strategieRobotników.strategiePracy.StrategiaPracy;
import main.symulacja.strategieRobotników.strategieProdukcji.StrategiaProdukcji;
import main.symulacja.fabryka.Fabryka;
import main.symulacja.giełda.oferty.OfertaRobotnika;
import main.symulacja.produkty.Produkt;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import static main.symulacja.Symulacja.*;
import static main.symulacja.Symulacja.TypyProduktów.*;

public class Robotnik extends Agent {
    private ŚcieżkaKariery[] ścieżki;
    private ŚcieżkaKariery obecnaŚcieżka;
    private StrategiaKariery strategiaKariery;
    private StrategiaKupna strategiaKupna;
    private StrategiaPracy strategiaPracy;
    private StrategiaProdukcji strategiaProdukcji;
    private int[] produktywność;
    private int produkcjaWObecnejTurze;
    private int licznikGłodu;
    private boolean czySięUbrał;

    public Robotnik (int idRobotnika, int poziom, String kariera, StrategiaKupna strategiaKupna,
                     StrategiaProdukcji strategiaProdukcji, StrategiaPracy strategiaPracy, StrategiaKariery strategiaKariery,
                     Map<Zawody, Integer> produktywność, Map<Produkt, Double> zasoby, Giełda giełda) {

        this.ścieżki = new ŚcieżkaKariery[ILE_ZAWODÓW];
        for (Symulacja.Zawody zawód: Symulacja.Zawody.values()) {
            int id = Symulacja.ID_KARIERY.get(zawód);
            // TODO - temp fix

            if (kariera == zawód.toString().toLowerCase(Locale.ROOT)) {
                this.ścieżki[id] = Fabryka.stwórzŚcieżkęKariery(kariera, poziom);
                this.obecnaŚcieżka = this.ścieżki[id];
            } else {
                this.ścieżki[id] = Fabryka.stwórzŚcieżkęKariery(zawód.toString().toLowerCase(Locale.ROOT), 1);
            }
        }

        this.strategiaKariery = strategiaKariery;
        this.strategiaKupna = strategiaKupna;
        this.strategiaPracy = strategiaPracy;
        this.strategiaProdukcji = strategiaProdukcji;
        this.id = idRobotnika;
        this.giełda = giełda;
        this.produktywność = new int[ILE_PRODUKTÓW];
        this.licznikGłodu = 0;
        this.czySięUbrał = true;
        this.produkcjaWObecnejTurze = 0;

        this.strategiaKariery.ustawRobotnika(this);
        this.strategiaKupna.ustawRobotnika(this);
        this.strategiaPracy.ustawRobotnika(this);
        this.strategiaProdukcji.ustawRobotnika(this);
    }

    public void ustawDzisiejsząProdukcję(int ileWyprodukował) {
        this.produkcjaWObecnejTurze = ileWyprodukował;
    }

    public int podajProduktywność(Symulacja.TypyProduktów produkt) {
        int id = Symulacja.ID_PRODUKTU.get(produkt);
        return produktywność[id];
    }

    public int ileUbrańJutro() {
        int ileUbrań = 0;
        TreeMap<Produkt, Double> ubrania = podajProdukty(UBRANIA);

        for (Produkt ubranie: ubrania.keySet()) {
            // Ubrania będą posortowane po wytrzymałości więc kiedy dojdziemy do wytrzymałości 1 to następne
            // mają na pewno nie lepszą wytrzymałość
            if (ubranie.podajWytrzymałość() == 1) {
                break;
            } else {
                ileUbrań += ileProduktów(ubranie);
            }

            if (ileUbrań >= 100) {
                break;
            }
        }

        return ileUbrań;
    };

    public int ileProgramówBrakuje() {
        int ileProgramów = 0;
        TreeMap<Produkt, Double> programy = podajProdukty(PROGRAMY);

        for (Produkt program: programy.keySet()) {
            ileProgramów += ileProduktów(program);
        }

        return produkcjaWObecnejTurze - ileProgramów;
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
        TreeMap<Produkt, Double> narzędzia = produkty.get(Symulacja.ID_PRODUKTU.get(NARZĘDZIA));
        for (Produkt produkt: narzędzia.keySet()) {
            if (produkt.podajTyp() == Symulacja.TypyProduktów.NARZĘDZIA) {
                bonus += narzędzia.get(produkt) * produkt.podajPoziom();
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

    private int podajBonusZUbrań() {
        if (czySięUbrał) {
            return 0;
        } else {
            return -this.podajGiełdę().podajKaręZaUbrania();
        }
    }

    // TODO - bazowa produkcja inna niż 100
    private void policzProduktywność() {
        for (Symulacja.TypyProduktów typ: Symulacja.TypyProduktów.values()) {
            int id = Symulacja.ID_PRODUKTU.get(typ);
            produktywność[id] = 100 + obecnaŚcieżka.podajBonus(typ) + podajBonusZNarzędzi() + podajBonusZGłodu() + podajBonusZUbrań();
        }
    }

    // Życie
    public void pracuj() {
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

    private void zużyjJedzenie() {
        if (!zużyjProdukty(100, new Produkt(JEDZENIE, 1))) {
            licznikGłodu++;
        }
    }

    private void zużyjUbranie(int ile, Produkt ubranie, TreeMap<Produkt, Double> ubraniaKopia) {
        int wytrzymałość = ubranie.podajWytrzymałość();

        // Usuwamy zużyte ubrania
        ubraniaKopia.put(ubranie, ileProduktów(ubranie) - ile);

        // Jeśli ubranie wciąż będzie miało wytrzymałość, to dodajemy te "zużyte" ubrania z powrotem na mapę
        // (oczywiście z wytrzymałością obniżoną o 1)
        if (wytrzymałość > 1) {
            Produkt zużyteUbranie = new Produkt(ubranie.podajTyp(), ubranie.podajPoziom(), wytrzymałość - 1);
            ubraniaKopia.put(zużyteUbranie, ileProduktów(ubranie) + ile);
        }
    }

    private void zużyjUbrania() {
        int licznikZużytych = 0;
        // Tworzymy kopię, aby móc od razu dodawać zużyte ubrania na mapę. Jeśli byśmy tego nie zrobili, to
        // takie zużyte ubranie mogłoby zostać ponownie rozpatrzone i zużyte wielokrotnie.
        TreeMap<Produkt, Double> ubrania = podajProdukty(UBRANIA);
        TreeMap<Produkt, Double> ubraniaKopia = new TreeMap<Produkt, Double>(ubrania);

        for (Produkt ubranie: ubrania.keySet()) {
            int zużyte = Math.min(DZIENNE_ZUŻYCIE_UBRAŃ - licznikZużytych, ubrania.get(ubranie).intValue());
            this.zużyjUbranie(zużyte, ubranie, ubraniaKopia);
            licznikZużytych += zużyte;

            if (licznikZużytych >= 100) {
                break;
            }
        }

        czySięUbrał = licznikZużytych >= 100;

        // Podmieniamy mapę.
        produkty.set(Symulacja.ID_PRODUKTU.get(UBRANIA), ubraniaKopia);
    }

    public void zużyjNarzędzia() {
        // Wyrzucamy wszystko tworząc nową mapę
        produkty.set(Symulacja.ID_PRODUKTU.get(NARZĘDZIA), new TreeMap<>(new KomparatorProduktów()));
    }

    public void zużyjPrzedmioty() {
        zużyjJedzenie();
        zużyjUbrania();
        zużyjNarzędzia();
        // Programy komputerowe są zużywane na bieżąco podczas produkcji
    };

    @Override
    public String toString() {
        return "Robotnik{" +
                "idRobotnika=" + id +
                '}';
    }
}
