package hospital.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "hospitals")
public class StorageListWrapper {

    private List<TableConstructor> hospitals;

    @XmlElement(name = "storage")
    public List<TableConstructor> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<TableConstructor> hospitals) {
        this.hospitals = hospitals;
    }
}
