package main.java.eus.opendata.modeloa;

public class EgunekoDatu {
    private int eguna;
    private String balioa;
    private String baliozkotzea;

    public EgunekoDatu(){
    }

    public EgunekoDatu(int eguna, String balioa, String baliozkotzea) {
        this.eguna = eguna;
        this.balioa = balioa;
        this.baliozkotzea = baliozkotzea;
    }

    public int getEguna() {
        return eguna;
    }
    public void setEguna(int eguna) {
        this.eguna = eguna;
    }
    public String getBalioa() {
        return balioa;
    }
    public void setBalioa(String balioa) {
        this.balioa = balioa;
    }
    public String getBaliozkotzea() {
        return baliozkotzea;
    }
    public void setBaliozkotzea(String baliozkotzea) {
        this.baliozkotzea = baliozkotzea;
    }
}
