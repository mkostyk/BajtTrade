package symulacja.agenci;

public abstract class Agent {
    private double diamenty;

    public double ileDiamentów() {
        return diamenty;
    }

    public void dodajDiamenty (double ile) {
        diamenty += ile;
    }
}
