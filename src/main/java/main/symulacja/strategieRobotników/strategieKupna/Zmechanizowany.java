package main.symulacja.strategieRobotników.strategieKupna;

import com.squareup.moshi.Json;

import main.symulacja.giełda.oferty.OfertaRobotnika;

import static main.Main.TypyProduktów.NARZEDZIA;

public class Zmechanizowany extends Czyścioszek {
    @Json(name = "liczba_narzedzi")
    protected final int liczbaNarzędzi;

    public Zmechanizowany(int liczbaNarzędzi) {
        this.liczbaNarzędzi = liczbaNarzędzi;
    }

    @Override
    public void dokonajZakupów() {
        super.dokonajZakupów();
        robotnik.podajGiełdę().dodajOfertęKupnaRobotnika(new OfertaRobotnika(NARZEDZIA, liczbaNarzędzi, robotnik));
    }
}
