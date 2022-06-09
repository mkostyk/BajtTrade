package main.symulacja.strategieRobotników.strategieProdukcji;

import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.giełda.oferty.OfertaRobotnika;
import main.symulacja.produkty.Produkt;
import main.symulacja.strategieRobotników.Strategia;

public abstract class StrategiaProdukcji extends Strategia {
    public abstract void wyprodukuj();
    public void wystawProdukty(Produkt produkt, int ile) {
        if (produkt.podajTyp() == Symulacja.TypyProduktów.DIAMENTY) {
            robotnik.dodajDiamenty(ile);
            //robotnik.dodajProdukty(ile, produkt);
        } else {
            robotnik.podajGiełdę().dodajOfertęSprzedażyRobotnika(new OfertaRobotnika(produkt, ile, this.robotnik));
        }
    }
}
