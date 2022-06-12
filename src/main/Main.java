package main;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import main.symulacja.Symulacja;
import main.symulacja.adapters.*;
import main.symulacja.komparatory.KomparatorProduktów;
import main.symulacja.produkty.Produkt;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    // TODO - widoczności
    // TODO - produkty czy enum produktów?
    // TODO - zgadzające się nazwy parametrów
    // TODO - kolejność produktów
    // TODO - jak najmniej .get, jak najwięcej ileProduktów
    // TODO - string zamiast enum chyba jest lepsze

    public static String typToString(Symulacja.TypyProduktów typ) {
        return typ.toString().toLowerCase(Locale.ROOT);
    }

    public static TreeMap<Produkt, Double> stwórzMapęCen(Map<String, Double> mapa) {
        TreeMap<Produkt, Double> wynik = new TreeMap<>(new KomparatorProduktów());

        for (Symulacja.TypyProduktów typ: Symulacja.TypyProduktów.values()) {
            wynik.put(new Produkt(typ, 1), mapa.get(typToString(typ)));
        }

        return wynik;
    }

    public static ArrayList<TreeMap<Produkt, Double>> stwórzListęMapProduktów(Map<String, Double> mapa) {
        ArrayList<TreeMap<Produkt, Double>> wynik = new ArrayList<>();
        for (Symulacja.TypyProduktów typ: Symulacja.TypyProduktów.values()) {
            TreeMap<Produkt, Double> mapaProduktu = new TreeMap<>(new KomparatorProduktów());
            mapaProduktu.put(new Produkt(typ, 1), mapa.get(typToString(typ)));
            wynik.add(mapaProduktu);
        }

        return wynik;
    }

    // TODO - nazwy po polsku
    private static String readFile() {
        try(BufferedReader br = new BufferedReader(new FileReader("input.json"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            return sb.toString();
        } catch (IOException e) {
            return null;
        }
    }

    // TODO - exception
    public static void main(String[] args) throws IOException {
        String json = readFile();
        assert json != null;

        Moshi moshi = new Moshi.Builder()
                .add(new SymulacjaAdapter())
                .add(new RobotnikAdapter())
                .add(new ŚcieżkaKarieryAdapter())
                .add(new StrategiaKupnaAdapter())
                .add(new StrategiaProdukcjiAdapter())
                .add(new StrategiaPracyAdapter())
                .add(new StrategiaKarieryAdapter())
                .add(new SpekulantAdapter())
                .build();

        JsonAdapter<Symulacja> jsonAdapter = moshi.adapter(Symulacja.class);
        Symulacja symulacja = jsonAdapter.fromJson(json);
        assert symulacja != null;
        System.out.println(symulacja);

        symulacja.symuluj();
        String jsonWyjście = jsonAdapter.toJson(symulacja);
        System.out.println(jsonWyjście);


        /*TreeMap<Produkt, Double> ceny = new TreeMap<>(new KomparatorProduktów());
        ceny.put(new Produkt(NARZĘDZIA, 1), 2.0);
        ceny.put(new Produkt(PROGRAMY, 1), 1.3);
        ceny.put(new Produkt(JEDZENIE, 1), 0.5);
        ceny.put(new Produkt(UBRANIA, 1), 5.3);

        Giełda giełda = Fabryka.stwórzGiełdę("kapitalistyczna", ceny, 30);
        giełda.ustawDzień(1);

        StrategiaKariery s1 = Fabryka.stwórzStrategięKariery("konserwatysta");
        StrategiaKupna s2 = Fabryka.stwórzStrategięKupna("czyścioszek");
        StrategiaPracy s3 = Fabryka.stwórzStrategięPracy("oszczędny", 1500);
        StrategiaProdukcji s4 = Fabryka.stwórzStrategięProdukcji("losowy");

        Robotnik robol1 = new Robotnik(ROLNIK, s1, s2, s3, s4, 1, giełda);

        OfertaRobotnika ofertaJedzenie = new OfertaRobotnika(JEDZENIE, 100, robol1);
        OfertaRobotnika ofertaUbrania = new OfertaRobotnika(UBRANIA, 100, robol1);

        //giełda.dodajOfertęKupnaRobotnika(ofertaJedzenie);
        //giełda.dodajOfertęKupnaRobotnika(ofertaUbrania);
        //giełda.wypisz();

        Robotnik robol2 = new Robotnik(ROLNIK, s1, s2, s3, s4, 2, giełda);
        robol2.dodajDiamenty(100);
        robol2.przeżyjDzień();

        giełda.posortujOferty();
        //giełda.wypisz();

        Spekulant spekulant = Fabryka.stwórzSpekulanta("średni", 11, giełda);

        spekulant.dodajProdukty(50, new Produkt(JEDZENIE, 1));
        spekulant.dodajProdukty(200, new Produkt(UBRANIA, 1));
        spekulant.dodajProdukty(200, new Produkt(NARZĘDZIA, 1));
        spekulant.dodajProdukty(200, new Produkt(PROGRAMY, 1));

        spekulant.dodajDiamenty(1000);
        spekulant.wystawOferty();

        giełda.wypisz();
        System.out.println();
        System.out.println(robol2.ileDiamentów() + " " + spekulant.ileDiamentów());

        giełda.dopasujOferty();
        giełda.wypisz();
        System.out.println();
        System.out.println(robol2.ileDiamentów() + " " + spekulant.ileDiamentów());

        giełda.skupOferty();
        giełda.wypisz();
        System.out.println();
        System.out.println(robol2.ileDiamentów() + " " + spekulant.ileDiamentów());

        robol2.zużyjPrzedmioty();*/
    }
}
