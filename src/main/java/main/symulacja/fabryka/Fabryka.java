/*
    Klasa tworząca obiekty odpowiedniego typu w zależności od uzyskanych argumentów.
 */
package main.symulacja.fabryka;

import main.symulacja.agenci.robotnicy.scieżkiKariery.*;
import main.symulacja.agenci.spekulanci.strategieSpekulantów.Regulujący;
import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.agenci.spekulanci.strategieSpekulantów.Wypukły;
import main.symulacja.agenci.spekulanci.strategieSpekulantów.Średni;
import main.symulacja.giełda.Giełda;
import main.symulacja.produkty.Produkt;
import main.symulacja.strategieRobotników.strategieKariery.Konserwatysta;
import main.symulacja.strategieRobotników.strategieKariery.Rewolucjonista;
import main.symulacja.strategieRobotników.strategieKariery.StrategiaKariery;
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
    public static Spekulant stwórzSpekulanta(int id, String strategia, Map<String, Double> zasoby,
                                             Integer historiaSpekulantaŚredniego) {
        return switch (strategia) {
            case "regulujacy_rynek" -> new Regulujący(id, zasoby);
            case "wypukly" -> new Wypukły(id, zasoby);
            case "sredni" -> new Średni(id, zasoby, historiaSpekulantaŚredniego);
            default -> null;
        };
    }
}
