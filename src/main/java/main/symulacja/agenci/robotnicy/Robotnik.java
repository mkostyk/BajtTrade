package main.symulacja.agenci.robotnicy;

import main.symulacja.agenci.robotnicy.scieżkiKariery.ŚcieżkaKariery;
import main.Main;
import main.symulacja.agenci.Agent;
import main.symulacja.komparatory.KomparatorProduktów;
import main.symulacja.strategieRobotników.strategieKariery.StrategiaKariery;
import main.symulacja.strategieRobotników.strategieKupna.StrategiaKupna;
import main.symulacja.strategieRobotników.strategiePracy.StrategiaPracy;
import main.symulacja.strategieRobotników.strategieProdukcji.StrategiaProdukcji;
import main.symulacja.fabryka.Fabryka;
import main.symulacja.produkty.Produkt;

import java.util.*;

import static main.Main.*;
import static main.Main.TypyProduktów.*;

public class Robotnik extends Agent {
    private final ŚcieżkaKariery[] ścieżki;
    private ŚcieżkaKariery obecnaŚcieżka;
    private final StrategiaKariery strategiaKariery;
    private final StrategiaKupna strategiaKupna;
    private final StrategiaPracy strategiaPracy;
    private final StrategiaProdukcji strategiaProdukcji;
    private final Map<String, Integer> bazowaProduktywność;
    private final Map<String, Integer> produktywność;
    private int produkcjaWObecnejTurze;
    private int licznikGłodu;
    private boolean czyPracował;

    public Robotnik (int idRobotnika, int poziom, String kariera, StrategiaKupna strategiaKupna,
                     StrategiaProdukcji strategiaProdukcji, StrategiaPracy strategiaPracy, String strategiaKariery,
                     Map<String, Integer> produktywność, Map<String, Double> zasoby) {
        super(idRobotnika, zasoby);
        this.ścieżki = new ŚcieżkaKariery[ILE_ZAWODÓW];
        for (Zawody zawód: Zawody.values()) {
            int id = zawód.ordinal();

            if (Objects.equals(kariera, Main.enumToString(zawód))) {
                this.ścieżki[id] = Fabryka.stwórzŚcieżkęKariery(kariera, poziom);
                this.obecnaŚcieżka = this.ścieżki[id];
            } else {
                this.ścieżki[id] = Fabryka.stwórzŚcieżkęKariery(Main.enumToString(zawód), 1);
            }
        }

        this.strategiaKariery = Fabryka.stwórzStrategięKariery(strategiaKariery);
        this.strategiaKupna = strategiaKupna;
        this.strategiaPracy = strategiaPracy;
        this.strategiaProdukcji = strategiaProdukcji;
        this.bazowaProduktywność = produktywność;
        this.produktywność = new TreeMap<>(produktywność);
        this.licznikGłodu = 0;
        this.produkcjaWObecnejTurze = 0;

        this.strategiaKariery.ustawRobotnika(this);
        this.strategiaKupna.ustawRobotnika(this);
        this.strategiaPracy.ustawRobotnika(this);
        this.strategiaProdukcji.ustawRobotnika(this);
    }
    public ŚcieżkaKariery podajKarierę() {
        return obecnaŚcieżka;
    }
    public StrategiaKupna podajStrategięKupna() {
        return strategiaKupna;
    }
    public StrategiaProdukcji podajStrategięProdukcji() {
        return strategiaProdukcji;
    }
    public StrategiaPracy podajStrategięPracy() {
        return strategiaPracy;
    }
    public StrategiaKariery podajStrategięKariery() {
        return strategiaKariery;
    }
    public int podajProduktywność(TypyProduktów produkt) {
        return produktywność.get(Main.enumToString(produkt));
    }
    public Map<String, Integer> podajBazoweProduktywności() {
        return bazowaProduktywność;
    }

    public void ustawDzisiejsząProdukcję(int ileWyprodukował) {
        this.produkcjaWObecnejTurze = ileWyprodukował;
    }


    private int ileUbrańZWytrzymałościąJeden() {
        int ileUbrań = 0;
        Map<Produkt, Double> ubrania = podajProdukty(UBRANIA);

        for (Produkt ubranie: ubrania.keySet()) {
            if (ubranie.podajWytrzymałość() == 1) {
                ileUbrań += ileProduktów(ubranie);
            }
        }

        return ileUbrań;
    }

    private int ileUbrań() {
        int ileUbrań = 0;
        Map<Produkt, Double> ubrania = podajProdukty(UBRANIA);

        for (Produkt ubranie: ubrania.keySet()) {
            ileUbrań += ileProduktów(ubranie);
        }

        return ileUbrań;
    }

    public int ileUbrańPotrzebneNaJutro() {
        int ileUbrańZWytrzymałościąPonadJeden = ileUbrań() - ileUbrańZWytrzymałościąJeden();
        // Następnego dnia rano na pewno będziemy mieli ileUbrańZWytrzymałościąPonadJeden ubrań. Pozostałe musimy
        // dokupić. Możemy jednak pechowo zakupić ubrania poziomu 1, które zostaną zużyte jeszcze dzisiaj, więc
        // aby zabezpieczyć się przed tym przypadkiem, musimy mieć co najmniej dwu krotność brakującej nam liczby ubrań.
        return Math.max(2 * (DZIENNE_ZUŻYCIE_UBRAŃ - ileUbrańZWytrzymałościąPonadJeden) - ileUbrańZWytrzymałościąJeden(), 0);
    }

    public int ileProgramówBrakuje() {
        int ileProgramów = 0;
        Map<Produkt, Double> programy = podajProdukty(PROGRAMY);

        for (Produkt program: programy.keySet()) {
            ileProgramów += ileProduktów(program);
        }

        return Math.max(0, produkcjaWObecnejTurze - ileProgramów);
    }

    // BONUSY
    private int podajBonusZNarzędzi() {
        int bonus = 0;
        Map<Produkt, Double> narzędzia = produkty.get(NARZEDZIA.ordinal());
        for (Produkt produkt: narzędzia.keySet()) {
            if (produkt.podajTyp() == NARZEDZIA) {
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
        if (ileUbrań() >= 100) {
            return 0;
        } else {
            return -podajGiełdę().podajKaręZaUbrania();
        }
    }

    private void policzProduktywność() {
        for (TypyProduktów typ: TypyProduktów.values()) {
            String typString = Main.enumToString(typ);
            produktywność.put(typString, bazowaProduktywność.get(typString) + obecnaŚcieżka.podajBonus(typ) +
                              podajBonusZNarzędzi() + podajBonusZGłodu() + podajBonusZUbrań());
        }
    }

    // Życie
    public void pracuj() {
        policzProduktywność();
        strategiaProdukcji.wyprodukuj();
        zużyjNarzędzia();
        strategiaKupna.dokonajZakupów();

        czyPracował = true;
    }

    public void uczSię() {
        if (strategiaKariery.czyZmienia()) {
            int idNowejKariery = strategiaKariery.podajNowyZawód().ordinal();
            obecnaŚcieżka = ścieżki[idNowejKariery];
        } else {
            int idObecnejKariery = obecnaŚcieżka.podajZawód().ordinal();
            obecnaŚcieżka.dodajPoziom();
            ścieżki[idObecnejKariery] = obecnaŚcieżka;
        }

        czyPracował = false;
    }

    private void zużyjJedzenie() {
        if (!zużyjProdukty(100, new Produkt(JEDZENIE, 1))) {
            licznikGłodu++;
        }
    }

    private void zużyjUbranie(int ile, Produkt ubranie, Map<Produkt, Double> ubraniaKopia) {
        int wytrzymałość = ubranie.podajWytrzymałość();

        // Usuwamy zużyte ubrania
        ubraniaKopia.put(ubranie, ileProduktów(ubranie) - ile);

        // Jeśli ubranie wciąż będzie miało wytrzymałość, to dodajemy te "zużyte" ubrania z powrotem na mapę
        // (oczywiście z wytrzymałością obniżoną o 1)
        if (wytrzymałość > 1) {
            Produkt zużyteUbranie = new Produkt(ubranie.podajTyp(), ubranie.podajPoziom(), wytrzymałość - 1);
            ubraniaKopia.put(zużyteUbranie, ileProduktów(zużyteUbranie) + ile);
        }
    }

    private void zużyjUbrania() {
        int licznikZużytych = 0;
        // Tworzymy kopię, aby móc od razu dodawać zużyte ubrania na mapę. Jeśli byśmy tego nie zrobili, to
        // takie zużyte ubranie mogłoby zostać ponownie rozpatrzone i zużyte wielokrotnie.
        Map<Produkt, Double> ubrania = podajProdukty(UBRANIA);
        Map<Produkt, Double> ubraniaKopia = new TreeMap<>(new KomparatorProduktów());
        ubraniaKopia.putAll(ubrania);

        for (Produkt ubranie: ubrania.keySet()) {
            int zużyte = Math.min(DZIENNE_ZUŻYCIE_UBRAŃ - licznikZużytych, ubrania.get(ubranie).intValue());
            this.zużyjUbranie(zużyte, ubranie, ubraniaKopia);
            licznikZużytych += zużyte;

            if (licznikZużytych >= 100) {
                break;
            }
        }

        // Podmieniamy mapę.
        produkty.set(UBRANIA.ordinal(), ubraniaKopia);
    }

    private void zużyjNarzędzia() {
        // Wyrzucamy wszystko tworząc nową mapę
        produkty.set(NARZEDZIA.ordinal(), new TreeMap<>(new KomparatorProduktów()));
    }

    public void zużyjPrzedmioty() {
        // Jeśli robotnik nie pracuje to nie zużywa produktów
        if (czyPracował) {
            zużyjJedzenie();
            zużyjUbrania();
            // Narzędzia i programy komputerowe są zużywane na bieżąco podczas produkcji
            // dla uproszczenia kodu.
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
