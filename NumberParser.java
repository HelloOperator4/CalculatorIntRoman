public class NumberParser {

    private boolean isRoman;
    private int value;

    public NumberParser(final String scan) throws Exception {
        try {
            value = Integer.parseInt(scan);
            isRoman = true;
        } catch (NumberFormatException e) {
            try {
                value = Converter.romanToInt(scan);
                isRoman = false;
            } catch (IllegalArgumentException ex) {
            }
        }
        if(value < 1 || value > 10) throw new NumberFormatException("Числа не в диапазоне от 1 до 10");
    }

    public int getValue() {
        return value;
    }
    public boolean getIsRoman() {
        return isRoman;
    }
}
