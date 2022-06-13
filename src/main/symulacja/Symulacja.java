package main.symulacja;

import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.giełda.Giełda;
import main.symulacja.utils.BazoweInformacje;
import main.Main;
import main.symulacja.fabryka.Fabryka;
import main.symulacja.produkty.Produkt;

import java.util.*;

public class Symulacja {
    private final int długość;
    private final List<Robotnik> robotnicy;
    private final List<Spekulant> spekulanci;
    private final Giełda giełda;
    public Symulacja (BazoweInformacje info, List<Robotnik> robotnicy, List<Spekulant> spekulanci) {
        this.długość = info.podajDługość();
        this.robotnicy = robotnicy;
        this.spekulanci = spekulanci;
        Map<Produkt, Double> cenyZerowe = Main.stwórzMapęCen(info.podajCeny());
        this.giełda = Fabryka.stwórzGiełdę(info.podajGiełdę(), cenyZerowe, info.podajKaręZaBrakUbrań());

        for (Robotnik robotnik: this.robotnicy) {
            robotnik.ustawGiełdę(this.giełda);
        }

        for (Spekulant spekulant: this.spekulanci) {
            spekulant.ustawGiełdę(this.giełda);
        }
    }

    public BazoweInformacje podajInformacje() {
        return new BazoweInformacje(długość, giełda.podajKaręZaUbrania(), new TreeMap<>(), giełda.toString());
    }

    public List<Robotnik> podajRobotników() {
        return robotnicy;
    }

    public List<Spekulant> podajSpekulantów() {
        return spekulanci;
    }

    private void wypiszDzień() {
        System.out.println(this);
    }

    private void dzień() {
        // Robotnicy (pkt 1)
        // Wywołujemy przeżyjDzień() oraz usuwamy robotników, jeśli nie udało im się przeżyć
        robotnicy.removeIf(robotnik -> !robotnik.przeżyjDzień());

        // Spekulanci (pkt 2)
        for (Spekulant spekulant: spekulanci) {
            spekulant.wystawOferty();
        }


        // Giełda (pkt 3 i 4)
        giełda.dopasujOferty();
        giełda.skupOferty();
        giełda.podsumujDzień();


        // Zużywanie przedmiotów (pkt 5)
        for (Robotnik robotnik: robotnicy) {
            robotnik.zużyjPrzedmioty();
            robotnik.usuńZbędneProdukty();
        }

        // Czyszczenie zasobów spekulantów
        for (Spekulant spekulant: spekulanci) {
            spekulant.usuńZbędneProdukty();
        }

        wypiszDzień();
    }

    public void symuluj() {
        for (int i = 0; i < długość; i++) {
            dzień();
        }
    }

    @Override
    public String toString() {
        return "Symulacja{" +
                "długość=" + długość +
                ", robotnicy=" + robotnicy +
                ", spekulanci=" + spekulanci +
                ", giełda=" + giełda +
                '}';
    }
}
