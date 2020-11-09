import java.util.*;

public class Converter {

    private static final NavigableMap<Integer, String> intToRomanMap;
    private static final Map<String, Integer> romanToIntMap;

    static {
        NavigableMap<Integer, String> irMap = new TreeMap<>();
        irMap.put(1000, "M");
        irMap.put(900, "CM");
        irMap.put(500, "D");
        irMap.put(400, "CD");
        irMap.put(100, "C");
        irMap.put(90, "XC");
        irMap.put(50, "L");
        irMap.put(40, "XL");
        irMap.put(10, "X");
        irMap.put(9, "IX");
        irMap.put(5, "V");
        irMap.put(4, "IV");
        irMap.put(1, "I");
        intToRomanMap = Collections.unmodifiableNavigableMap(irMap);
        HashMap<String, Integer> riMap = new HashMap<>();
        riMap.put("M", 1000);
        riMap.put("CM", 900);
        riMap.put("D", 500);
        riMap.put("CD", 400);
        riMap.put("C", 100);
        riMap.put("XC", 90);
        riMap.put("L", 50);
        riMap.put("XL", 40);
        riMap.put("X", 10);
        riMap.put("IX", 9);
        riMap.put("V", 5);
        riMap.put("IV", 4);
        riMap.put("I", 1);
        romanToIntMap = Collections.unmodifiableMap(riMap);
    }

    public static String intToRoman(int intValue) throws Exception {
        if (intValue > 1000 || intValue < 1) {
            throw new Exception("Ошибка конвертации! Конвертируемое число должно находиться в пределах от 1 до 1000");
        }
        final StringBuilder result = new StringBuilder();
        for (Integer key : intToRomanMap.descendingKeySet()) {
            while (intValue >= key) {
                intValue -= key;
                result.append(intToRomanMap.get(key));
            }
        }
        return result.toString();
    }

    public static int romanToInt(String romanValue) throws Exception {
        if (romanValue == null || romanValue.isEmpty()) {
            throw new Exception("Ошибка конвертации! Невозможно конвертировать пустую строку");
        }
        int result = 0;
        while (romanValue.length() > 0) {
            String subStr = (romanValue.length() > 1) ? romanValue.substring(0, 2) : romanValue;
            Integer value = romanToIntMap.get(subStr);
            if (value == null) {
                subStr = romanValue.substring(0, 1);
            }
            value = romanToIntMap.get(subStr);
            if (value == null) {
                throw new Exception(String.format("Ошибка конвертации! Невозможно конвертировать подстроку %s", subStr));
            }
            result += value.intValue();
            romanValue = romanValue.substring(subStr.length());
        }
        return result;
    }
}
