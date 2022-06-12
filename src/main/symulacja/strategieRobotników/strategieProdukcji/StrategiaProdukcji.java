package main.symulacja.strategieRobotników.strategieProdukcji;

import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.giełda.oferty.OfertaRobotnika;
import main.symulacja.produkty.Produkt;
import main.symulacja.strategieRobotników.Strategia;
import static main.symulacja.Symulacja.TypyProduktów.*;

import java.util.TreeMap;

public abstract class StrategiaProdukcji extends Strategia {
    public abstract void wyprodukuj();
    public void użyjProgramówIWystawProdukty(Produkt produkt, int ile) {
        System.out.println(produkt + " " + ile);
        robotnik.ustawDzisiejsząProdukcję(ile);
        if (produkt.podajTyp() == JEDZENIE || produkt.podajTyp() == DIAMENTY) {
            robotnik.dodajProdukty(ile, produkt);
            wystawProdukty(produkt, ile);
            return;
        }

        int ileZużytychProgramów = 0;
        TreeMap<Produkt, Double> programy = robotnik.podajProdukty(PROGRAMY);
        for (Produkt program: programy.keySet()) {
            int zużyte = (int) Math.min(ile - ileZużytychProgramów, programy.get(program));
            Produkt nowyProdukt = new Produkt(produkt.podajTyp(), program.podajPoziom());

            programy.put(program, programy.get(program) - zużyte);
            robotnik.dodajProdukty(zużyte, nowyProdukt); // TODO - zamiana argumentów
            robotnik.zużyjProdukty(zużyte, program);
            wystawProdukty(nowyProdukt, zużyte);
            ileZużytychProgramów += zużyte;

            if (ileZużytychProgramów >= ile) {
                break;
            }
        }

        robotnik.dodajProdukty(ile - ileZużytychProgramów, produkt);
        wystawProdukty(produkt, ile - ileZużytychProgramów);
    }

    private void wystawProdukty(Produkt produkt, int ile) {
        if (produkt.podajTyp() == Symulacja.TypyProduktów.DIAMENTY) {
            robotnik.dodajDiamenty(ile);
            //robotnik.dodajProdukty(ile, produkt);
        } else {
            robotnik.podajGiełdę().dodajOfertęSprzedażyRobotnika(new OfertaRobotnika(produkt, ile, this.robotnik));
        }
    }
}
