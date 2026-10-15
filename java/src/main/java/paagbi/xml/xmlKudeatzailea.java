package paagbi.xml;

import paagbi.modeloa.Neurketa;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

/**
 * XML fitxategiak irakurri eta idazteko metodoak eskaintzen ditu.
 */
public class xmlKudeatzailea {

    /**
     * Neurketen zerrenda XML fitxategi batean gordetzen du.
     *
     * @param neurketak gorde beharreko neurketak
     * @param fitxategia XML fitxategiaren bidea
     * @throws Exception fitxategia idaztean edo XMLa sortzean errorea gertatzen bada
     */
    public void gorde(List<Neurketa> neurketak, String fitxategia)
            throws Exception {

        // JAXB prestatzen da DatuMultzoa klasea XML bihurtzeko.
        JAXBContext testuingurua =
                JAXBContext.newInstance(DatuMultzoa.class);

        Marshaller marshaller =
                testuingurua.createMarshaller();

        // XMLa formatu irakurgarrian sortzen du.
        marshaller.setProperty(
                Marshaller.JAXB_FORMATTED_OUTPUT,
                true
        );

        // Neurketak XMLaren erro-elementuan biltzen dira.
        DatuMultzoa datuak = new DatuMultzoa();
        datuak.setNeurketak(neurketak);

        // XML fitxategia sortzen du.
        marshaller.marshal(
                datuak,
                new File(fitxategia)
        );
    }

    /**
     * XML fitxategi bateko neurketak irakurtzen ditu.
     */
    public List<Neurketa> irakurri(String fitxategia)
            throws Exception {

        // JAXB prestatzen da XMLa Java objektu bihurtzeko.
        JAXBContext testuingurua =
                JAXBContext.newInstance(DatuMultzoa.class);

        Unmarshaller unmarshaller =
                testuingurua.createUnmarshaller();

        // XML fitxategia irakurri eta DatuMultzoa objektua sortzen du.
        DatuMultzoa datuak =
                (DatuMultzoa) unmarshaller.unmarshal(
                        new File(fitxategia)
                );

        // Irakurritako neurketen zerrenda itzultzen du.
        return datuak.getNeurketak();
    }
}