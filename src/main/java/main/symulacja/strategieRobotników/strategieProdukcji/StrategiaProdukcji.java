package main.symulacja.strategieRobotników.strategieProdukcji;

import main.Main;
import main.symulacja.giełda.oferty.OfertaRobotnika;
import main.symulacja.produkty.Produkt;
import main.symulacja.strategieRobotników.Strategia;
import static main.Main.TypyProduktów.*;

import java.util.Map;

public abstract class StrategiaProdukcji extends Strategia {
    public abstract void wyprodukuj();
    protected void użyjProgramówIWystawProdukty(Main.TypyProduktów typ, int ile) {
        Produkt produkt;
        // Tylko programista wpływa na poziomy swoich produktów.
        if (robotnik.podajKarierę().podajZawód() == Main.Zawody.PROGRAMISTA) {
            produkt = new Produkt(typ, robotnik.podajKarierę().podajPoziom());
        } else {
            produkt = new Produkt(typ, 1);
        }
        robotnik.ustawDzisiejsząProdukcję(ile);

        if (!Main.PRODUKTY_Z_PROGRAMAMI.contains(typ)) {
            wystawProdukty(ile, produkt);
            return;
        }

        int ileZużytychProgramów = 0;
        Map<Produkt, Double> programy = robotnik.podajProdukty(PROGRAMY);
        for (Produkt program: programy.keySet()) {
            int zużyte = (int) Math.min(ile - ileZużytychProgramów, programy.get(program));
            Produkt nowyProdukt = new Produkt(typ, program.podajPoziom());

            programy.put(program, programy.get(program) - zużyte);
            robotnik.zużyjProdukty(zużyte, program);
            wystawProdukty(zużyte, nowyProdukt);
            ileZużytychProgramów += zużyte;

            if (ileZużytychProgramów >= ile) {
                break;
            }
        }

        wystawProdukty(ile - ileZużytychProgramów, produkt);
    }

    private void wystawProdukty(int ile, Produkt produkt) {
        if (produkt.podajTyp() == DIAMENTY) {
            robotnik.dodajDiamenty(ile);
        } else {
            robotnik.podajGiełdę().dodajOfertęSprzedażyRobotnika(new OfertaRobotnika(produkt, ile, this.robotnik));
        }
    }
}
