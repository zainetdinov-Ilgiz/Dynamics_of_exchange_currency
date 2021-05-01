public class Difference {
    private String currencyName;
    private float differenceValue;
    private float absDifferenceValue;

    public Difference(String currencyName, float differenceValue, float absDifferenceValue) {
        this.currencyName = currencyName;
        this.differenceValue = differenceValue;
        this.absDifferenceValue = absDifferenceValue;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public float getDifferenceValue() {
        return differenceValue;
    }

    public float getAbsDifferenceValue() {
        return absDifferenceValue;
    }
}
