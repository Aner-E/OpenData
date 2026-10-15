package paagbi.xml;

import main.java.eus.opendata.modeloa.Neurketa;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;

public class xmlKudeatzailea {

    public void gorde(List<Neurketa> neurketak, String fitxategia)
            throws Exception {

        JAXBContext testuingurua =
                JAXBContext.newInstance(DatuMultzoa.class);

        Marshaller marshaller =
                testuingurua.createMarshaller();

        marshaller.setProperty(
                Marshaller.JAXB_FORMATTED_OUTPUT,
                true
        );

        DatuMultzoa datuak = new DatuMultzoa();
        datuak.setNeurketak(neurketak);

        marshaller.marshal(
                datuak,
                new File(fitxategia)
        );
    }

    public List<Neurketa> irakurri(String fitxategia)
            throws Exception {

        JAXBContext testuingurua =
                JAXBContext.newInstance(DatuMultzoa.class);

        Unmarshaller unmarshaller =
                testuingurua.createUnmarshaller();

        DatuMultzoa datuak =
                (DatuMultzoa) unmarshaller.unmarshal(
                        new File(fitxategia)
                );

        return datuak.getNeurketak();
    }
}