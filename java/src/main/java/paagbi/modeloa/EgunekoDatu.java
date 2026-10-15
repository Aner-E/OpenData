package paagbi.modeloa;

/**
 * Egun jakin bateko neurketa eta haren baliozkotze-egoera gordetzen ditu.
 */
public class EgunekoDatu {

    private int eguna;
    private String balioa;
    private String baliozkotzea;

    /**
     * JAXB-k eta beste tresna batzuek objektua sortzeko erabiltzen duten eraikitzailea.
     */
    public EgunekoDatu() {
    }

    public EgunekoDatu(int eguna, String balioa, String baliozkotzea) {
        this.eguna = eguna;
        this.balioa = balioa;
        this.baliozkotzea = baliozkotzea;
    }

    // Neurketa egin den eguna eskuratu eta aldatzeko metodoak.
    public int getEguna() {
        return eguna;
    }

    public void setEguna(int eguna) {
        this.eguna = eguna;
    }

    // Eguneko neurketaren balioa eskuratu eta aldatzeko metodoak.
    public String getBalioa() {
        return balioa;
    }

    public void setBalioa(String balioa) {
        this.balioa = balioa;
    }

    // Datuaren baliozkotze-egoera eskuratu eta aldatzeko metodoak.
    public String getBaliozkotzea() {
        return baliozkotzea;
    }

    public void setBaliozkotzea(String baliozkotzea) {
        this.baliozkotzea = baliozkotzea;
    }
}