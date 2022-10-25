package Biudzetas;

import java.time.LocalDate;

public class IslaiduIrasas extends Irasas {
    public final static String irasoTipas = "Išlaidų-įrašas";

    private IslaiduKategorija kategorija;

    private String pavadinimas;

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public IslaiduIrasas() {
        super();
    }

    public IslaiduIrasas(double suma, LocalDate data, String papildomaInfo, IslaiduKategorija kategorija) {
        super(suma, data, papildomaInfo);
        this.kategorija = kategorija;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,Kategorija:%s",irasoTipas, super.toString(), kategorija );
    }

    public IslaiduKategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(IslaiduKategorija kategorija) {
        this.kategorija = kategorija;
    }

    @Override
    public String toCsv() {
        return String.format("%s,%s,%s", irasoTipas, super.toCsv(), kategorija.getNumeris());
    }
}