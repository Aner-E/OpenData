package main.java.eus.opendata.modeloa;

import java.util.ArrayList;
import java.util.List;

public class Neurketa {

    private String Probintzia;
    private String Udalerria;
    private String Estazioa;
    private String Magnitudea;
    private String LaginketaPuntua;
    private int Urtea;
    private int Hilabetea;

    private List<EgunekoDatu> datuak;

    public Neurketa (){
        datuak = new ArrayList<>();
    }

    public Neurketa( String Probintzia, String Udalerria, String Estazioa, String Magnitudea, String LaginketaPuntua, int Urtea, int Hilabetea){
        this.Probintzia = Probintzia;
        this.Udalerria = Udalerria;
        this.Estazioa = Estazioa;
        this.Magnitudea = Magnitudea;
        this.LaginketaPuntua = LaginketaPuntua;
        this.Urtea = Urtea;
        this.Hilabetea = Hilabetea;
        this.datuak = new ArrayList<>();
    }
    public String getProbintzia() {
        return Probintzia;
    }
    
    public void setProbintzia(String Probintzia){
        this.Probintzia = Probintzia;
    }

    public String getUdalerria() {
        return Udalerria;
    }

    public void setUdalerria(String Udalerria) {
        this.Udalerria = Udalerria;
    }

    public String getEstazioa() {
        return Estazioa;
    }

    public void setEstazioa(String Estazioa) {
        this.Estazioa = Estazioa;
    }

    public String getMagnitudea() {
        return Magnitudea;
    }

    public void setMagnitudea(String Magnitudea) {
        this.Magnitudea = Magnitudea;
    }

    public String getLaginketaPuntua() {
        return LaginketaPuntua;
    }

    public void setLaginketaPuntua(String LaginketaPuntua) {
        this.LaginketaPuntua = LaginketaPuntua;
    }

    public int getUrtea() {
        return Urtea;
    }

    public void setUrtea(int Urtea) {
        this.Urtea = Urtea;
    }

    public int getHilabetea() {
        return Hilabetea;
    }

    public void setHilabetea(int Hilabetea) {
        this.Hilabetea = Hilabetea;
    }

    public List<EgunekoDatu> getDatuak() {
        return datuak;
    }

    public void setDatuak(List<EgunekoDatu> datuak) {
        this.datuak = datuak;
    }

    public void gehituDatu(EgunekoDatu datua) {
        datuak.add(datua);
    }
}