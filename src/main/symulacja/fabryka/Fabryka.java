package main.symulacja.fabryka;

import main.symulacja.agenci.robotnicy.scieżkiKariery.*;
import main.symulacja.agenci.spekulanci.Regulujący;
import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.agenci.spekulanci.Wypukły;
import main.symulacja.agenci.spekulanci.Średni;
import main.symulacja.giełda.Giełda;
import main.symulacja.produkty.Produkt;
import main.symulacja.strategieRobotników.strategieKariery.Konserwatysta;
import main.symulacja.strategieRobotników.strategieKariery.Rewolucjonista;
import main.symulacja.strategieRobotników.strategieKariery.StrategiaKariery;
import main.symulacja.strategieRobotników.strategieKupna.*;
import main.symulacja.strategieRobotników.strategiePracy.*;
import main.symulacja.strategieRobotników.strategieProdukcji.*;
import main.symulacja.giełda.strategieGiełdy.Kapitalistyczna;
import main.symulacja.giełda.strategieGiełdy.Socjalistyczna;
import main.symulacja.giełda.strategieGiełdy.Zrównoważona;

import java.util.Map;

public class Fabryka {
    // SCIEŻKI KARIERY
    public static ŚcieżkaKariery stwórzŚcieżkęKariery(String kariera, int poziom) {
        return switch (kariera) {
            case "gornik" -> new Górnik(poziom);
            case "rolnik" -> new Rolnik(poziom);
            case "inzynier" -> new Inżynier(poziom);
            case "programista" -> new Programista(poziom);
            case "rzemieslnik" -> new Rzemieślnik(poziom);
            default -> null;
        };
    }

    // STRATEGIE KARIERY
    public static StrategiaKariery stwórzStrategięKariery(String strategia) {
        return switch (strategia) {
            case "konserwatysta" -> new Konserwatysta();
            case "rewolucjonista" -> new Rewolucjonista();
            default -> null;
        };
    }

    // STRATEGIE KUPNA

    public static StrategiaKupna stwórzStrategięKupna(String strategia, int ile_narzędzi) {
        return switch (strategia) {
            case "technofob" -> new Technofob();
            case "czyscioszek" -> new Czyścioszek();
            case "zmechanizowany" -> new Zmechanizowany(ile_narzędzi);
            case "gadzeciarz" -> new Gadżeciarz(ile_narzędzi);
            default -> null;
        };
    }

    // STRATEGIE PRACY

    public static StrategiaPracy stwórzStrategięPracy(String strategia, int limitDiamentów,
                                                      int zapas, int okres, int okresowośćNauki) {
        return switch (strategia) {
            case "pracus" -> new Pracuś();
            case "rozkladowy" -> new Rozkładowy();
            case "okresowy" -> new Okresowy(okresowośćNauki);
            case "oszczedny" -> new Oszczędny(limitDiamentów);
            case "student" -> new Student(zapas, okres);
            default -> null;
        };
    }

    // STRATEGIE PRODUKCJI

    public static StrategiaProdukcji stwórzStrategięProdukcji(String strategia, int historiaŚrednia, int historiaPerspektywy) {
        return switch (strategia) {
            case "chciwy" -> new Chciwy();
            case "krotkowzroczny" -> new Krótkowzroczny();
            case "losowy" -> new Losowy();
            case "perspektywiczny" -> new Perspektywiczny(historiaPerspektywy);
            case "sredniak" -> new Średniak(historiaŚrednia);
            default -> null;
        };
    }

    // GIEŁDA
    public static Giełda stwórzGiełdę(String strategia, Map<Produkt, Double> ceny, int karaZaUbrania) {
        return switch (strategia) {
            case "kapitalistyczna" -> new Kapitalistyczna(ceny, karaZaUbrania);
            case "socjalistyczna" -> new Socjalistyczna(ceny, karaZaUbrania);
            case "zrownowazona" -> new Zrównoważona(ceny, karaZaUbrania);
            default -> null;
        };
    }

    // SPEKULANCI
    public static Spekulant stwórzSpekulanta(int id, String strategia,
                                             Map<String, Double> zasoby, int historiaSpekulantaŚredniego) {
        return switch (strategia) {
            case "regulujacy_rynek" -> new Regulujący(id, zasoby);
            case "wypukly" -> new Wypukły(id, zasoby);
            case "sredni" -> new Średni(id, zasoby, historiaSpekulantaŚredniego);
            default -> null;
        };
    }
}
