package paagbi.xml;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import paagbi.modeloa.Neurketa;

/**
 * XML fitxategian neurketa guztiak multzokatzeko erabiltzen den klasea.
 */
@XmlRootElement(name = "datuMultzoa")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatuMultzoa {

    // XML barruan neurketa bakoitza elementu bereizi gisa gordetzen da.
    @XmlElement(name = "neurketa")
    private List<Neurketa> neurketak = new ArrayList<>();

    /**
     * JAXB-k objektua sortzeko behar duen eraikitzaile hutsa.
     */
    public DatuMultzoa() {
    }

    /**
     * Multzoko neurketa guztiak itzultzen ditu.
     */
    public List<Neurketa> getNeurketak() {
        return neurketak;
    }

    /**
     * Multzoko neurketen zerrenda ezartzen du.
     */
    public void setNeurketak(List<Neurketa> neurketak) {
        this.neurketak = neurketak;
    }
}