package main.symulacja.adaptery;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.Main;
import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.agenci.spekulanci.strategieSpekulantów.Średni;
import main.symulacja.fabryka.Fabryka;

import java.util.Map;

@SuppressWarnings("UnusedDeclaration")
public class SpekulantAdapter {
    private static class KarieraJson {
        private String typ;
        private Integer historia_spekulanta_sredniego = null;
    }

    private static class SpekulantJson {
        protected int id;
        protected KarieraJson kariera;
    }

    private static class SpekulantInputJson extends SpekulantJson {
        private Map <String, Double> zasoby;
    }

    private static class SpekulantOutputJson extends SpekulantJson {
        private Map <String, Double[]> zasoby;
    }

    @ToJson
    private SpekulantOutputJson toJson(Spekulant spekulant) {
        SpekulantOutputJson json = new SpekulantOutputJson();
        json.kariera = new KarieraJson();

        if (spekulant instanceof Średni) {
            json.kariera.historia_spekulanta_sredniego = ((Średni) spekulant).podajParametr();
        }

        json.id = spekulant.podajID();
        json.kariera.typ = spekulant.toString();
        json.zasoby = Main.stwórzMapęTablicProduktów(spekulant);
        return json;
    }

    @FromJson
    private Spekulant fromJson(SpekulantInputJson spekulant) {
        return Fabryka.stwórzSpekulanta(spekulant.id, spekulant.kariera.typ, spekulant.zasoby,
                                        spekulant.kariera.historia_spekulanta_sredniego);
    }
}
