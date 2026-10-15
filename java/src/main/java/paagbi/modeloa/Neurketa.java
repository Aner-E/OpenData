package paagbi.modeloa;

import java.util.ArrayList;
import java.util.List;

/**
 * Airearen kalitateari buruzko neurketa baten datuak gordetzen ditu.
 * Neurketa bakoitzak kokapenaren informazioa, urtea, hilabetea
 * eta eguneko datuen zerrenda ditu.
 */
public class Neurketa {

    private String Probintzia;
    private String Udalerria;
    private String Estazioa;
    private String Magnitudea;
    private String LaginketaPuntua;
    private int Urtea;
    private int Hilabetea;

    // Neurketa horri dagozkion eguneko datuak gordetzen ditu.
    private List<EgunekoDatu> datuak;

    /**
     * Neurketa huts bat sortzen du.
     */
    public Neurketa() {
        datuak = new ArrayList<>();
    }

    public Neurketa(String Probintzia, String Udalerria, String Estazioa,
                    String Magnitudea, String LaginketaPuntua,
                    int Urtea, int Hilabetea) {

        this.Probintzia = Probintzia;
        this.Udalerria = Udalerria;
        this.Estazioa = Estazioa;
        this.Magnitudea = Magnitudea;
        this.LaginketaPuntua = LaginketaPuntua;
        this.Urtea = Urtea;
        this.Hilabetea = Hilabetea;
        this.datuak = new ArrayList<>();
    }

    // Probintziaren izena edo kodea eskuratu eta aldatzeko metodoak.
    public String getProbintzia() {
        return Probintzia;
    }

    public void setProbintzia(String Probintzia) {
        this.Probintzia = Probintzia;
    }

    // Udalerriaren izena edo kodea eskuratu eta aldatzeko metodoak.
    public String getUdalerria() {
        return Udalerria;
    }

    public void setUdalerria(String Udalerria) {
        this.Udalerria = Udalerria;
    }

    // Neurketa egin den estazioa eskuratu eta aldatzeko metodoak.
    public String getEstazioa() {
        return Estazioa;
    }

    public void setEstazioa(String Estazioa) {
        this.Estazioa = Estazioa;
    }

    // Neurtutako magnitudea eskuratu eta aldatzeko metodoak.
    public String getMagnitudea() {
        return Magnitudea;
    }

    public void setMagnitudea(String Magnitudea) {
        this.Magnitudea = Magnitudea;
    }

    // Laginketa-puntuaren identifikatzailea eskuratu eta aldatzeko metodoak.
    public String getLaginketaPuntua() {
        return LaginketaPuntua;
    }

    public void setLaginketaPuntua(String LaginketaPuntua) {
        this.LaginketaPuntua = LaginketaPuntua;
    }

    // Neurketaren urtea eskuratu eta aldatzeko metodoak.
    public int getUrtea() {
        return Urtea;
    }

    public void setUrtea(int Urtea) {
        this.Urtea = Urtea;
    }

    // Neurketaren hilabetea eskuratu eta aldatzeko metodoak.
    public int getHilabetea() {
        return Hilabetea;
    }

    public void setHilabetea(int Hilabetea) {
        this.Hilabetea = Hilabetea;
    }

    // Eguneko datuen zerrenda eskuratu eta ordezkatzeko metodoak.
    public List<EgunekoDatu> getDatuak() {
        return datuak;
    }

    public void setDatuak(List<EgunekoDatu> datuak) {
        this.datuak = datuak;
    }

    /**
     * Eguneko datu berri bat gehitzen dio neurketari.
     *
     * @param datua gehitu nahi den eguneko datua
     */
    public void gehituDatu(EgunekoDatu datua) {
        datuak.add(datua);
    }
}