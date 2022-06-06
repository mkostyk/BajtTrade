package symulacja.giełda;

import symulacja.utils.PodsumowanieDnia;

public abstract class Giełda {
    public abstract int podajDzień();
    public abstract PodsumowanieDnia[] podajHistorięCen();
}
