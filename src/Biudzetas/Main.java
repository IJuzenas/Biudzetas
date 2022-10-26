package Biudzetas;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    static Biudzetas biudzetas = new Biudzetas();
    static Irasas irasas = new Irasas();

    public static void main(String[] args) throws IOException {

        boolean run = true;
        while (run) {
            int pasirinkimas = menu(scanner);
            switch (pasirinkimas) {
                case 1:
                    irasuIvedimas(scanner);
                    break;
                case 2:
                   gautiIrasus(scanner);
                    break;
                case 3:
                    trintiIrasa(scanner, irasas, biudzetas);
                    break;
                case 4:
                    redaguotiIrasa(scanner, irasas, biudzetas);
                    break;
                case 5:
                    paskaiciuotiBalansa(biudzetas);
                    break;
                case 6:
                    File.saugotiDuomenis(biudzetas.gautiVisusIrasus());
                    break;
                case 7:
                    biudzetas.gautiVisusIrasus().addAll(File.gautiDuomenis());
                    break;
                case 8:
                    System.out.println("Programos pabaiga");
                    scanner.close();
                    break;
                default:
                    System.out.println("Pasirinkite iš siūlomo menu:");
                    menu(scanner);
            }
        }
        while (true) ;
    }


    private static int menu(Scanner scanner) {
        System.out.println("Menu:\n 1- Įvesti įrašą\n 2 - Gauti įrašus\n 3 - Trinti įrašą\n 4 - Redaguoti įrašą\n 5 - Balansas\n 6 -Saugoti duomenis į failą\n 7- Gauti duomenis iš failo\n 8- Exit\n");
        return skPasirinkimas(scanner, 1, 2, 3, 4, 5, 6, 7, 8);
    }

    private static int skPasirinkimas(Scanner scanner, int... leistinosReiksmes) {
        int pasirinkimas = 0;
        boolean neivesta = true;
        while (neivesta) {
            String ivestiDuomenys = scanner.next();
            try {
                pasirinkimas = Integer.parseInt(ivestiDuomenys);
                if (leistinosReiksmes.length == 0 || arSkaiciusYraSarase(leistinosReiksmes, pasirinkimas)) {
                    neivesta = false;
                } else {
                    System.out.println("Nezinoma komanda");
                    menu(scanner);
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Klaida! Ivestas ne skaicius");
                break;
            }
        }
        return pasirinkimas;
    }

    private static int irasoPasirinkimas(Scanner scanner) {
        System.out.println(
                "Pasirinkite:\n" +
                        " 1- pajamų\n" +
                        " 2- išlaidų");
        return skPasirinkimas(scanner, 1, 2, 3);
    }
    private static void irasuIvedimas (Scanner scanner) {
        int irasoPasirinkimas = irasoPasirinkimas(scanner);
        switch (irasoPasirinkimas) {
            case 1:
                biudzetas.pridetiIrasa(naujasPajamuIrasas(scanner));
                break;
            case 2:
                biudzetas.pridetiIrasa(naujasIslaiduIrasas(scanner));
                break;
            default:
                System.out.println("Pasirinkite iš siūlomų variantų");
        }
    }
    private static boolean arSkaiciusYraSarase(final int[] skaiciuSarasas, final int skaicius) {
        boolean result = false;
        for (int i : skaiciuSarasas) {
            if (i == skaicius) {
                result = true;
                break;
            }
        }
        return result;
    }
    private static void gautiIrasus(Scanner scanner) {
        int pasirinktiIrasus = pasirinktiIrasus(scanner);
        switch (pasirinktiIrasus) {

            case 1:
                spausdintiIrasus(biudzetas.gautiVisusPajamuIrasus());
                break;
            case 2:
                spausdintiIrasus(biudzetas.gautiVisusIslaiduIrasus());
                break;
            case 3:
                spausdintiIrasus(biudzetas.gautiVisusIrasus());
                break;
            default:
                System.out.println("Įveskite simbolį iš siūlomų pasirinkimų!");
                menu(scanner);
        }
    }

    private static int pasirinktiIrasus(Scanner scanner) {
        System.out.println(
                "1 - pajamu irasas\n" +
                        "2 - islaidu irasas\n" +
                        "3 - visos išlaidos");
        return skPasirinkimas(scanner, 1, 2, 3);
    }


    private static void paskaiciuotiBalansa(Biudzetas biudzetas) {
        float balansas = biudzetas.balansas();
        System.out.println(String.format("Balansas: %.2fEur", balansas));
    }

    private static void redaguotiIrasa(Scanner scanner, Irasas irasas, Biudzetas biudzetas) {
        System.out.println("Įveskite įrašo ID:");
        long id = skPasirinkimas(scanner);
        irasas = biudzetas.gautiIrasaPagalID(id);
        if (irasas.getId() == 0) {
            System.out.println("Klaida! Tokio įrašo nėra!");
        } else {
            if (irasas != null) {
                System.out.println("Suma: " + irasas.getSuma() + " Pakeisti? T/N");
                String pasirinkimas = scanner.next();
                if (pasirinkimas.toUpperCase().equals("T")) {
                    System.out.println("Įveskite naują sumą: ");
                    irasas.setSuma(scanner.nextDouble());
                }
                if (irasas instanceof PajamuIrasas) {
                    System.out.println("Kategorija: " + ((PajamuIrasas) irasas).getKategorija().getPavadinimas() + " Pakeisti? T/N");
                    String pasirinkimas1 = scanner.next();
                    if (pasirinkimas1.toUpperCase().equals("T")) {
                        System.out.println("Pasirinkite naują kategoriją: ");
                        for (PajamuKategorija pk : PajamuKategorija.values()) {
                            System.out.println(pk);
                        }
                        int kat = scanner.nextInt();
                        ((PajamuIrasas) irasas).setKategorija(PajamuKategorija.kategorijaPagalNR(kat));
                    }
                } else if (irasas instanceof IslaiduIrasas) {
                    System.out.println("Kategorija: " + ((IslaiduIrasas) irasas).getKategorija().getPavadinimas() + " Pakeisti? T/N");
                    String pasirinkimas2 = scanner.next();
                    if (pasirinkimas2.toUpperCase().equals("T")) {
                        System.out.println("Pasirinkite naują kategoriją: ");
                        for (IslaiduKategorija ik : IslaiduKategorija.values()) {
                            System.out.println(ik);
                        }
                        int kat = scanner.nextInt();
                        ((IslaiduIrasas) irasas).setKategorija(IslaiduKategorija.islaiduKategorijaPagalNR(kat));
                    }
                }
                System.out.println("Papildoma informacija: " + irasas.getPapildomaInfo() + " Pakeisti? T/N");
                String pap = scanner.next();
                if (pap.toUpperCase().equals("T")) {
                    System.out.println("Įveskite papildomą informaciją");
                    String newPap = scanner.next();
                    irasas.setPapildomaInfo(newPap);
                    biudzetas.atnaujintiIrasa(irasas);
                } else {
                    System.out.println("Programa veikia toliau");
                }
            }
            assert irasas != null;
            if (id != irasas.getId()) {
                System.out.println("Klaida! Tokio įrašo nėra!");
            }
        }
    }


    private static void trintiIrasa(Scanner scanner, Irasas irasas, Biudzetas biudzetas) {
        System.out.println("Įveskite ID:");
        long id = skPasirinkimas(scanner);
        if (Objects.equals(id, irasas.getId())) {
            System.out.println("Klaida! Tokio įrašo nėra!");
        }  else {
            biudzetas.pasalintiIrasa(id);
        }
        }
        private static void spausdintiIrasus(ArrayList<Irasas> irasai) {
        for (Irasas irasas : irasai) {
            System.out.println(irasas);
        }
    }
    private static Irasas naujasIslaiduIrasas(Scanner scanner) {
        IslaiduIrasas islaiduIrasas = new IslaiduIrasas();
        System.out.println("Suma: ");
        try {
            islaiduIrasas.setSuma(scanner.nextDouble());
        } catch (NumberFormatException nfe) {
            System.out.println("Klaida! Ivestas ne skaicius");
        }
        System.out.println("Kategorija: ");
        for (IslaiduKategorija ik : IslaiduKategorija.values()) {
            System.out.println(ik);
        }
        int pasirinkimas2 = scanner.nextInt();
        islaiduIrasas.setKategorija(IslaiduKategorija.islaiduKategorijaPagalNR(pasirinkimas2));
        System.out.println("Papildoma informacija:");
        islaiduIrasas.setPapildomaInfo(scanner.next());
        islaiduIrasas.setData(LocalDate.now());
        System.out.println(islaiduIrasas);
        return islaiduIrasas;
    }

    private static Irasas naujasPajamuIrasas(Scanner scanner) {
        PajamuIrasas pajamuIrasas = new PajamuIrasas();
        System.out.println("Suma: ");
        try {
            pajamuIrasas.setSuma(scanner.nextDouble());
        } catch (NumberFormatException nfe) {
            System.out.println("Klaida! Ivestas ne skaicius");
        }
        System.out.println("Kategorija: ");
        for (PajamuKategorija pk : PajamuKategorija.values()) {
            System.out.println(pk);
        }
        int pasirinkimas = scanner.nextInt();
        pajamuIrasas.setKategorija(PajamuKategorija.kategorijaPagalNR(pasirinkimas));
        System.out.println("Papildoma informacija: ");
        String papild = scanner.next();
        pajamuIrasas.setPapildomaInfo(papild);

        pajamuIrasas.setData(LocalDate.now());
        System.out.println(pajamuIrasas);
        return pajamuIrasas;
    }
}
