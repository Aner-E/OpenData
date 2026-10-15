package paagbi.xml;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import paagbi.modeloa.Neurketa;

@XmlRootElement(name = "datuMultzoa")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatuMultzoa {

    @XmlElement(name = "neurketa")
    private List<Neurketa> neurketak = new ArrayList<>();

    public DatuMultzoa() {
    }

    public List<Neurketa> getNeurketak() {
        return neurketak;
    }

    public void setNeurketak(List<Neurketa> neurketak) {
        this.neurketak = neurketak;
    }
}