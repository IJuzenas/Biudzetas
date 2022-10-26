package Biudzetas;

import java.time.LocalDate;

public class Irasas {
    private static long counter = 0;
    private long id;
    private double suma;
    private LocalDate data;
    private String papildomaInfo;

    public Irasas () {
        this.id = counter++;
    }
    public Irasas (double suma, LocalDate data, String papildomaInfo) {
        this.suma = suma;
        this.data = data;
        this.papildomaInfo = papildomaInfo;
    }

    @Override
    public String toString() {
        return String.format("ID:%d,Suma=%.2f,Data:%s,PapildomaInfo:%s", id, suma, data, papildomaInfo);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Irasas && id == ((Irasas)obj).getId()) {
            return true;
        } else {
            return false;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getPapildomaInfo() {
        return papildomaInfo;
    }

    public void setPapildomaInfo(String papildomaInfo) {
        this.papildomaInfo = papildomaInfo;
    }

    public String toCsv() {
        return String.format("ID:%d,%.2f,%s,%s", id, suma, data, papildomaInfo);
    }
}
