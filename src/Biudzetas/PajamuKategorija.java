package Biudzetas;

public enum PajamuKategorija {
    ATLYGINIMAS(1, "Atyginimas"),
    IŠMOKOS(2, "Išmokos"),
    AVANSAS(3, "Avansas");

    private int numeris;
    private String pavadinimas;

    PajamuKategorija(int i, String z) {
        numeris = i;
        pavadinimas = z;
    }
    public static PajamuKategorija kategorijaPagalNR (int i) {
        for (PajamuKategorija pk : values()) {
            if (pk.getNumeris()==i) {
                return pk;
            }
        } return null;
    }

    public int getNumeris() {
        return numeris;
    }

    public void setNumeris(int numeris) {
        this.numeris = numeris;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    @Override
    public String toString() {
        return String.format("%d,%s", numeris, pavadinimas);
    }
}
