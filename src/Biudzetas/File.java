package Biudzetas;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class File {


    public File(String failas) {
    }


    public static void saugotiDuomenis(ArrayList<Irasas> irasai) {
        PrintWriter pw = null;
        try {
            FileWriter fr = new FileWriter("src/failas.csv", false);
            BufferedWriter bf = new BufferedWriter(fr);
            pw = new PrintWriter(bf);
            for (Irasas irasas : irasai) {
                pw.println(irasas.toCsv());
            }
        } catch (IOException e) {
            System.out.println("Sistemos klaida. Nepavyko įrašyti į failą.");
        }catch (NullPointerException npe) {
            System.out.println("Printwritteris negali uzsidaryti");
        } finally {
            assert pw != null;
            pw.close();
            System.out.println("Duomenys sėkmingai išsaugoti į failą!");
        }
    }

    public static ArrayList<Irasas> gautiDuomenis() throws IOException {
        ArrayList<Irasas> irasai = new ArrayList<Irasas>();
        String linija = "";
        String path = "src/failas.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((linija = br.readLine()) != null) {
                String[] data = linija.split(",");
                irasai.add(irasasIsCSV(linija));
//                irasai.add(irasasIsCSV(linija));
            }
        } catch (IOException e) {
            System.out.println("Sistemos klaida. Nepavyko nuskaityti duomenų iš failo.");
        }
        return irasai;
    }

    private static Irasas irasasIsCSV(String csv) {
        String[] data = csv.split(",");
        Irasas irasas = null;
        if (PajamuIrasas.irasoTipas.equals(data[0])) {
            PajamuIrasas pajamuIrasas = new PajamuIrasas();
//          pajamuIrasas.setId(Long.parseLong(data[1]));
            pajamuIrasas.setSuma(Double.parseDouble(data[2]));
            pajamuIrasas.setData(LocalDate.parse(data[3]));
            pajamuIrasas.setPapildomaInfo(data[4]);
            pajamuIrasas.setKategorija(PajamuKategorija.kategorijaPagalNR(Integer.parseInt(data[5])));
            irasas = pajamuIrasas;
        } else if (IslaiduIrasas.irasoTipas.equals(data[0])) {
            IslaiduIrasas islaiduIrasas = new IslaiduIrasas();
//          islaiduIrasas.setId(Long.parseLong(data[1]));
            islaiduIrasas.setSuma(Double.parseDouble(data[2]));
            islaiduIrasas.setData(LocalDate.parse(data[3]));
            islaiduIrasas.setPapildomaInfo(data[4]);
            islaiduIrasas.setKategorija(IslaiduKategorija.islaiduKategorijaPagalNR(Integer.parseInt(data[5])));
            irasas = islaiduIrasas;
        }
        return irasas;
    }
}
