package Biudzetas;
import java.time.LocalDate;

public class PajamuIrasas extends Irasas {

    public final static String irasoTipas = "Pajamu-irasas";

    private PajamuKategorija kategorija;

    private String pavadinimas;

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public PajamuIrasas() {
        super();
    }

    public PajamuIrasas(double suma, LocalDate data, String papildomaInfo, PajamuKategorija kategorija) {
        super(suma, data, papildomaInfo);
        this.kategorija = kategorija;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,Kategorija:%s", irasoTipas, super.toString(), kategorija);
    }

    public PajamuKategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(PajamuKategorija kategorija) {
        this.kategorija = kategorija;
    }

    @Override
    public String toCsv() {
            return String.format("%s,%s,%s,%s", irasoTipas, super.toCsv(), kategorija.getNumeris(), kategorija.getPavadinimas());
        }
    }

