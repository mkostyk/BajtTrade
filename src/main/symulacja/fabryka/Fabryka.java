package main.symulacja.fabryka;

import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.agenci.robotnicy.scieżkiKariery.*;
import main.symulacja.agenci.spekulanci.Regulujący;
import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.agenci.spekulanci.Wypukły;
import main.symulacja.agenci.spekulanci.Średni;
import main.symulacja.giełda.Giełda;
import main.symulacja.giełda.strategieGiełdy.Kapitalistyczna;
import main.symulacja.giełda.strategieGiełdy.Socjalistyczna;
import main.symulacja.giełda.strategieGiełdy.Zrównoważona;
import main.symulacja.produkty.Produkt;
import main.symulacja.strategieRobotników.strategieKariery.*;
import main.symulacja.strategieRobotników.strategieKupna.*;
import main.symulacja.strategieRobotników.strategiePracy.*;
import main.symulacja.strategieRobotników.strategieProdukcji.*;

import java.util.TreeMap;

public class Fabryka {
    // SCIEŻKI KARIERY
    public static ŚcieżkaKariery stwórzŚcieżkęKariery(String kariera, int poziom) {
        return switch (kariera) {
            case "górnik" -> new Górnik(poziom);
            case "rolnik" -> new Rolnik(poziom);
            case "inżynier" -> new Inżynier(poziom);
            case "programista" -> new Programista(poziom);
            case "rzemieślnik" -> new Rzemieślnik(poziom);
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
    public static StrategiaKupna stwórzStrategięKupna(String strategia) {
        return switch (strategia) {
            case "technofob" -> new Technofob();
            case "czyścioszek" -> new Czyścioszek();
            default -> null;
        };
    }

    public static StrategiaKupna stwórzStrategięKupna(String strategia, int ile_narzędzi) {
        return switch (strategia) {
            case "zmechanizowany" -> new Zmechanizowany(ile_narzędzi);
            case "gadżeciarz" -> new Gadżeciarz(ile_narzędzi);
            default -> null;
        };
    }


    // STRATEGIE PRACY
    public static StrategiaPracy stwórzStrategięPracy(String strategia) {
        return switch (strategia) {
            case "pracuś" -> new Pracuś();
            case "rozkładowy" -> new Rozkładowy();
            default -> null;
        };
    }

    public static StrategiaPracy stwórzStrategięPracy(String strategia, int parametr1) {
        return switch (strategia) {
            case "okresowy" -> new Okresowy(parametr1);
            case "oszczędny" -> new Oszczędny(parametr1);
            default -> null;
        };
    }

    public static StrategiaPracy stwórzStrategięPracy(String strategia, int parametr1, int parametr2) {
        return switch (strategia) {
            case "student" -> new Student(parametr1, parametr2);
            default -> null;
        };
    }

    // STRATEGIE PRODUKCJI
    public static StrategiaProdukcji stwórzStrategięProdukcji(String strategia) {
        return switch (strategia) {
            case "chciwy" -> new Chciwy();
            case "krótkowzroczny" -> new Krótkowzroczny();
            case "losowy" -> new Losowy();
            default -> null;
        };
    }

    public static StrategiaProdukcji stwórzStrategięProdukcji(String strategia, int ileDni) {
        return switch (strategia) {
            case "perspektywiczny" -> new Perspektywiczny(ileDni);
            case "średniak" -> new Średniak(ileDni);
            default -> null;
        };
    }

    // GIEŁDA
    public static Giełda stwórzGiełdę(String strategia, TreeMap<Produkt, Double> ceny, int karaZaUbrania) {
        return switch (strategia) {
            case "kapitalistyczna" -> new Kapitalistyczna(ceny, karaZaUbrania);
            case "socjalistyczna" -> new Socjalistyczna(ceny, karaZaUbrania);
            case "zrównoważona" -> new Zrównoważona(ceny, karaZaUbrania);
            default -> null;
        };
    }

    // SPEKULANCI
    public static Spekulant stwórzSpekulanta(String strategia, Giełda giełda) {
        return switch (strategia) {
            case "regulujący" -> new Regulujący(giełda);
            case "wypukły" -> new Wypukły(giełda);
            default -> null;
        };
    }

    public static Spekulant stwórzSpekulanta(String strategia, int ileDni, Giełda giełda) {
        return switch (strategia) {
            case "średni" -> new Średni(giełda, ileDni);
            default -> null;
        };
    }
}
