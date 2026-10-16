package paagbi.xml;

import paagbi.modeloa.Neurketa;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * XML fitxategiak irakurri eta idazteko metodoak eskaintzen ditu.
 */
public class xmlKudeatzailea {

    private final String fitxategia;

    /**
     * Eraikitzailea: XML fitxategia ez bada existitzen, hutsik sortzen du.
     *
     * @param fitxategia XML fitxategiaren bidea
     */
    public xmlKudeatzailea(String fitxategia) {
        this.fitxategia = fitxategia;
        sortuEzBadago();
    }

    /**
     * XML fitxategia existitzen ez bada, neurketarik gabeko fitxategi bat sortzen du.
     */
    private void sortuEzBadago() {
        File f = new File(fitxategia);

        if (!f.exists()) {
            try {
                // Karpeta gurasoa existitzen ez bada, sortu
                File gurasoa = f.getParentFile();
                if (gurasoa != null && !gurasoa.exists()) {
                    gurasoa.mkdirs();
                }

                gorde(new ArrayList<>());
            } catch (Exception e) {
                throw new RuntimeException(
                        "Ezin izan da XML fitxategia sortu: " + fitxategia, e);
            }
        }
    }

    /**
     * Neurketen zerrenda XML fitxategian gordetzen du.
     */
    public void gorde(List<Neurketa> neurketak) throws Exception {

        JAXBContext testuingurua =
                JAXBContext.newInstance(DatuMultzoa.class);

        Marshaller marshaller = testuingurua.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        DatuMultzoa datuak = new DatuMultzoa();
        datuak.setNeurketak(neurketak);

        marshaller.marshal(datuak, new File(fitxategia));
    }

    /**
     * XML fitxategiko neurketak irakurtzen ditu.
     */
    public List<Neurketa> irakurri() throws Exception {

        JAXBContext testuingurua =
                JAXBContext.newInstance(DatuMultzoa.class);

        Unmarshaller unmarshaller = testuingurua.createUnmarshaller();

        DatuMultzoa datuak =
                (DatuMultzoa) unmarshaller.unmarshal(new File(fitxategia));

        return datuak.getNeurketak();
    }
}