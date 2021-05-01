import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "ValCurs")
public class Currencies {
    @XmlAttribute(name = "Date")
    private String date;

    @XmlAttribute(name="name")
    private String name;

    @XmlElement(name = "Valute")
    private ArrayList<Currency> currencies;

    public Currencies() {
    }

    public Currencies(String date, String name, ArrayList<Currency> currencies) {
        this.date = date;
        this.name = name;
        this.currencies = currencies;
    }

    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}
