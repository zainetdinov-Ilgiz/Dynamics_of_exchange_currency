import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Valute")
public class Currency {
    @XmlAttribute(name="ID")
    String id ;

    @XmlElement(name="NumCode")
    int numericCode;

    @XmlElement(name="CharCode")
    String charCode;

    @XmlElement(name="Nominal")
    int nominal;

    @XmlElement(name="Name")
    String name;

    @XmlElement(name = "Value")
    String value;

    public Currency() {
    }

    public Currency(String id, int numericCode, String charCode, int nominal, String name, String value) {
        this.id = id;
        this.numericCode = numericCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public int getNumericCode() {
        return numericCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return Float.parseFloat(value.replace(',','.'));
    }

    @Override
    public String toString() {
        return String.format("Valute ID=%s, NumCode = %d, CharCode = %s, Nominal = %d, Name = %s, Value = %s \n",this.getId(),this.getNumericCode(),this.getCharCode(),this.getNominal(),this.getName(),this.getValue());
    }
}
