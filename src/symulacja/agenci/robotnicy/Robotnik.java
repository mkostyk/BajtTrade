package symulacja.agenci.robotnicy;

import symulacja.Symulacja;
import symulacja.agenci.Agent;
import symulacja.agenci.robotnicy.scieżkiKariery.ŚcieżkaKariery;
import symulacja.agenci.robotnicy.strategieKariery.StrategiaKariery;
import symulacja.agenci.robotnicy.strategieKupna.StrategiaKupna;
import symulacja.agenci.robotnicy.strategiePracy.StrategiaPracy;
import symulacja.agenci.robotnicy.strategieProdukcji.StrategiaProdukcji;
import symulacja.giełda.Giełda;
import symulacja.utils.PodsumowanieDnia;

public abstract class Robotnik extends Agent {
    protected ŚcieżkaKariery ścieżka;
    protected StrategiaKariery strategiaKariery;
    protected StrategiaKupna strategiaKupna;
    protected StrategiaPracy strategiaPracy;
    protected StrategiaProdukcji strategiaProdukcji;
    protected int[] produktywność;

    public abstract int podajProduktywność(Symulacja.TypyProduktów produkt);

    public abstract int ileUbrańJutro();
    public abstract int ileProgramówBrakuje();
}
