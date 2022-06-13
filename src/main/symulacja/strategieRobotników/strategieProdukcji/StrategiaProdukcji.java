package main.symulacja.strategieRobotników.strategieProdukcji;

import main.Main;
import main.symulacja.Symulacja;
import main.symulacja.giełda.oferty.OfertaRobotnika;
import main.symulacja.produkty.Produkt;
import main.symulacja.strategieRobotników.Strategia;
import static main.Main.TypyProduktów.*;

import java.util.Map;

public abstract class StrategiaProdukcji extends Strategia {
    public abstract void wyprodukuj();
    protected void użyjProgramówIWystawProdukty(Produkt produkt, int ile) {
        System.out.println(produkt + " " + ile);
        robotnik.ustawDzisiejsząProdukcję(ile);
        if (!Main.PRODUKTY_Z_POZIOMEM.contains(produkt.podajTyp())) {
            //robotnik.dodajProdukty(ile, produkt);
            wystawProdukty(ile, produkt);
            return;
        }

        int ileZużytychProgramów = 0;
        Map<Produkt, Double> programy = robotnik.podajProdukty(PROGRAMY);
        for (Produkt program: programy.keySet()) {
            int zużyte = (int) Math.min(ile - ileZużytychProgramów, programy.get(program));
            Produkt nowyProdukt = new Produkt(produkt.podajTyp(), program.podajPoziom());

            programy.put(program, programy.get(program) - zużyte);
            //robotnik.dodajProdukty(zużyte, nowyProdukt);
            robotnik.zużyjProdukty(zużyte, program);
            wystawProdukty(zużyte, nowyProdukt);
            ileZużytychProgramów += zużyte;

            if (ileZużytychProgramów >= ile) {
                break;
            }
        }

        //robotnik.dodajProdukty(ile - ileZużytychProgramów, produkt);
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
