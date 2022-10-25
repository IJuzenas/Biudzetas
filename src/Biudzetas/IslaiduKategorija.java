package Biudzetas;

public enum IslaiduKategorija {
    MAISTAS(1,"Maistas"),
    KOMUNALINIAI(2,"Komunaliniai"),
    BUITIS(3,"Buitis");

    private int numeris;
    private String pavadinimas;

    IslaiduKategorija (int i, String z) {
        numeris = i;
        pavadinimas = z;
    }
    public static IslaiduKategorija islaiduKategorijaPagalNR (int i) {
        for (IslaiduKategorija ik : values()) {
            if (ik.getNumeris()==i){
                return ik;
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
        return String.format("%d,%s,", numeris, pavadinimas);
    }
}

