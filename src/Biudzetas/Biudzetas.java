package Biudzetas;

import java.util.ArrayList;

public class Biudzetas {
    private ArrayList<Irasas> irasai = new ArrayList<>();
    public void pridetiIrasa(Irasas irasas) {
        irasai.add(irasas);
    }

    public Irasas gautiIrasaPagalID(long id) {
        for (Irasas irasas : irasai) {
            if (irasas.getId() == id) {
                return irasas;
            }
        }
        return null;
    }
    public ArrayList<Irasas> gautiVisusPajamuIrasus() {
        ArrayList<Irasas> pajamuIrasai = new ArrayList<Irasas>();
        for (Irasas irasas : irasai) {
            if (irasas instanceof PajamuIrasas) {
                pajamuIrasai.add(irasas);
            }
        } return pajamuIrasai;
    }
    public ArrayList<Irasas> gautiVisusIslaiduIrasus() {
        ArrayList<Irasas> islaiduIrasai = new ArrayList<Irasas>();
        for (Irasas irasas : irasai) {
            if (irasas instanceof IslaiduIrasas) {
                islaiduIrasai.add(irasas);
            }
        } return islaiduIrasai;
    }
    public ArrayList<Irasas> gautiVisusIrasus() {
        return irasai;
    }
    public float balansas() {
        float balansas = 0;
        for(Irasas irasas : irasai) {
            if (irasas instanceof PajamuIrasas) {
                balansas += irasas.getSuma();
            } else if (irasas instanceof IslaiduIrasas) {
                balansas -= irasas.getSuma();
            }
        }
        return balansas;
    }

    public boolean pasalintiIrasa(long id) {
        Irasas tusciasIrasas = new Irasas();
        tusciasIrasas.setId(id);
        return irasai.remove(tusciasIrasas);
    }

    public boolean atnaujintiIrasa(Irasas irasas) {
        pasalintiIrasa(irasas.getId());
        return irasai.add(irasas);
    }
}