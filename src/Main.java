import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        LocalDate presentDay = LocalDate.now();
        LocalDate yesterday ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String urlPattern = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=%s";
        String presentDayURL = String.format(urlPattern,presentDay.format(formatter));
        String yesterdayURL;
        ArrayList<Currency> presentDayCurrencies = null;
        ArrayList<Currency> yesterdayCurrencies = null;
        Currencies presentDayData = null;
        Currencies yesterdayData = null;
        Difference[] differences;
        try {
            JAXBContext context = JAXBContext.newInstance(Currencies.class,Currency.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            presentDayData = (Currencies) unmarshaller.unmarshal(new URL(presentDayURL));
            yesterday = LocalDate.parse(presentDayData.getDate().replace('.','/'),formatter).minusDays(1);
            yesterdayURL = String.format(urlPattern,yesterday.format(formatter));
            presentDayCurrencies = presentDayData.getCurrencies();
            yesterdayData = (Currencies) unmarshaller.unmarshal(new URL(yesterdayURL));
            yesterdayCurrencies = yesterdayData.getCurrencies();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        differences = new Difference[presentDayCurrencies.size()];
        for(int i =0;i<presentDayCurrencies.size();i++){
            for(int j= 0; j<yesterdayCurrencies.size();j++){
                if (presentDayCurrencies.get(i).getName().equals(yesterdayCurrencies.get(j).getName())) {
                    float k = presentDayCurrencies.get(i).getValue()-yesterdayCurrencies.get(j).getValue();
                    differences[i]=new Difference(presentDayCurrencies.get(i).getName(),k,Math.abs(k));
                }
            }
        }
        for(int i = differences.length-2;i>=0;i--){
            for(int j =0;j<=i;j++) {
                if (differences[j].getAbsDifferenceValue() < differences[j + 1].getAbsDifferenceValue()) {
                    Difference buff = differences[j];
                    differences[j] = differences[j + 1];
                    differences[j + 1] = buff;
                }
            }
        }
        String line = "__________________________________________________";
        String currencyName = "Наименование валюты";
        String changing = "Изменение";
        System.out.printf("%s%n%-40s%1s%7s%n%s%n",line,currencyName,"|",changing,line);
        for (int i =0;i<5;i++){
           System.out.printf("%-40s%1s%7f%n",differences[i].getCurrencyName(),"|",differences[i].getDifferenceValue());
        }
        System.out.println(line);
    }
}
