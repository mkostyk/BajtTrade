package main;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;
import main.symulacja.Symulacja;
import main.symulacja.agenci.Agent;
import main.symulacja.adapters.*;
import main.symulacja.komparatory.KomparatorProduktów;
import main.symulacja.produkty.Produkt;
import main.symulacja.strategieRobotników.strategieKupna.*;
import main.symulacja.strategieRobotników.strategiePracy.*;
import main.symulacja.strategieRobotników.strategieProdukcji.*;

import java.io.*;
import java.util.*;
import java.util.Map;

import static main.Main.TypyProduktów.*;

public class Main {
    // TODO - jak najmniej .get, jak najwięcej ileProduktów
    // TODO - pozbyć się typID()
    // TODO - poczyścić komentarze
    public static int DZIENNE_ZUŻYCIE_UBRAŃ = 100;
    public static int ILE_PRODUKTÓW = 5;
    public static int ILE_ZAWODÓW = 5;
    public enum TypyProduktów {JEDZENIE, UBRANIA, NARZEDZIA, DIAMENTY, PROGRAMY}
    public enum Zawody {ROLNIK, RZEMIESLNIK, INZYNIER, GORNIK, PROGRAMISTA}

    public static final Set<TypyProduktów> PRODUKTY_Z_POZIOMEM = Set.of(
            UBRANIA, NARZEDZIA, PROGRAMY
    );

    public static final Set<TypyProduktów> PRODUKTY_NA_GIEŁDZIE = Set.of(
            JEDZENIE, UBRANIA, NARZEDZIA, PROGRAMY
    );

    public static double INFINITY = 1e300;
    public static int MAX_POZIOM = Integer.MAX_VALUE;
    public static Random RNG = new Random();


    public static String enumToString(Object enumerator) {
        return enumerator.toString().toLowerCase(Locale.ROOT);
    }

    private static Double[] stwórzTablicęProduktów(Map<Produkt, Double> mapaCen) {
        if (mapaCen.size() == 0) {
            return new Double[0];
        } else {
            // TODO - do przetestowania
            Produkt pierwszy = mapaCen.keySet().stream().findFirst().get();
            Double[] wynik = new Double[pierwszy.podajPoziom()];
            TypyProduktów typ = pierwszy.podajTyp();

            for (int poziom = wynik.length; poziom > 0; poziom--) {
                // TODO
                wynik[poziom - 1] = mapaCen.getOrDefault(new Produkt(typ, poziom), 0.0);
            }

            return wynik;
        }
    }

    public static Map<String, Double[]> stwórzMapęTablicProduktów(Agent właściciel) {
        Map<String, Double[]> wynik = new TreeMap<>();
        for (TypyProduktów typ: TypyProduktów.values()) {
            wynik.put(enumToString(typ), stwórzTablicęProduktów(właściciel.podajProdukty(typ)));
        }

        return wynik;
    }

    public static Map<Produkt, Double> stwórzMapęCen(Map<String, Double> mapa) {
        Map<Produkt, Double> wynik = new TreeMap<>(new KomparatorProduktów());

        for (TypyProduktów typ: TypyProduktów.values()) {
            wynik.put(new Produkt(typ, 1), mapa.get(enumToString(typ)));
        }

        return wynik;
    }

    public static List<Map<Produkt, Double>> stwórzListęMapProduktów(Map<String, Double> mapa) {
        List<Map<Produkt, Double>> wynik = new ArrayList<>();
        for (TypyProduktów typ: TypyProduktów.values()) {
            Map<Produkt, Double> mapaProduktu = new TreeMap<>(new KomparatorProduktów());
            mapaProduktu.put(new Produkt(typ, 1), mapa.get(enumToString(typ)));
            wynik.add(mapaProduktu);
        }

        return wynik;
    }

    private static String wczytajPlik(String nazwa) {
        try(BufferedReader br = new BufferedReader(new FileReader(nazwa))) {
            StringBuilder sb = new StringBuilder();
            String linia = br.readLine();

            while (linia != null) {
                sb.append(linia);
                sb.append(System.lineSeparator());
                linia = br.readLine();
            }

            return sb.toString();
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        String json = wczytajPlik("src/main/input.json");
        assert json != null;

        Moshi moshi = new Moshi.Builder()
                // TODO - osobne klasy
                .add(PolymorphicJsonAdapterFactory.of(StrategiaPracy.class, "typ")
                        .withSubtype(Okresowy.class, "okresowy")
                        .withSubtype(Oszczędny.class, "oszczedny")
                        .withSubtype(Pracuś.class, "pracus")
                        .withSubtype(Rozkładowy.class, "rozkladowy")
                        .withSubtype(Student.class, "student")
                )

                .add(PolymorphicJsonAdapterFactory.of(StrategiaKupna.class, "typ")
                        .withSubtype(Czyścioszek.class, "czyscioszek")
                        .withSubtype(Gadżeciarz.class, "gadzeciarz")
                        .withSubtype(Technofob.class, "technofob")
                        .withSubtype(Zmechanizowany.class, "zmechanizowany")
                )

                .add(PolymorphicJsonAdapterFactory.of(StrategiaProdukcji.class, "typ")
                        .withSubtype(Chciwy.class, "chciwy")
                        .withSubtype(Krótkowzroczny.class, "krotkowzroczny")
                        .withSubtype(Losowy.class, "losowy")
                        .withSubtype(Perspektywiczny.class, "perspektywiczny")
                        .withSubtype(Średniak.class, "sredniak")
                )

                .add(new SymulacjaAdapter())
                .add(new RobotnikAdapter())
                .add(new ŚcieżkaKarieryAdapter())
                .add(new StrategiaKarieryAdapter())
                .add(new SpekulantAdapter())
                .build();

        JsonAdapter<Symulacja> jsonAdapter = moshi.adapter(Symulacja.class);
        Symulacja symulacja = jsonAdapter.fromJson(json);
        assert symulacja != null;
        System.out.println(symulacja);

        symulacja.symuluj();
        String jsonWyjście = jsonAdapter.indent("  ").toJson(symulacja);
        System.out.println(jsonWyjście);


        /*Map<Produkt, Double> ceny = new TreeMap<>(new KomparatorProduktów());
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
