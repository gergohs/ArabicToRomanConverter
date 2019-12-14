package arabictoromanconverter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author gergohs
 */
public class ArabicToRomanConverter {

    final static int MINROMAN = 1;
    final static int MAXROMAN = 3999;

    public static void main(String[] args) {

        System.out.println("Please enter an arabic number: ");
        Scanner sc = new Scanner(System.in);

        converter(sc.nextInt());

        //Optinal function - Check the working of the converter with some numbers given in the "testArray"
        //checkConverter();         
    }

    public static void converter(int inputArabic) {

        int resultOfDivision;
        int remainder = inputArabic;
        StringBuilder romanNum = new StringBuilder();
        Map<Integer, String> conversionTable = new HashMap<>();

        //************Fill a HashMap with the Key-Value pairs************
        List<Integer> keys = Arrays.asList(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1);
        List<String> values = Arrays.asList("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I");

        for (int i = 0; i < keys.size(); i++) {
            conversionTable.put(keys.get(i), values.get(i));
        }

        //************Create a TreeMap to sort the elements of the original HashMap in ascending order by Keys************
        TreeMap<Integer, String> tMap = new TreeMap<>(conversionTable);

        if (inputArabic < MINROMAN || inputArabic > MAXROMAN) {
            System.out.println("Please give an arabic number between 1 and 3999" + "\n");
        } else {
            for (Integer key : tMap.descendingKeySet()) {

                resultOfDivision = (inputArabic / key);

                if (remainder > 0) {
                    for (int x = 0; x < resultOfDivision; x++) {
                        romanNum.append(tMap.get(key));
                    }
                    remainder = remainder - (resultOfDivision * key);
                }
                /*
                //Some optinal print to explain the calculation
                System.out.println("Base num: " + inputArabic + " | key: " + key + " | " + "Key int the base: " + resultOfDivision + " | Remaider: " + remainder);
                System.out.println("*******************************************************************" + "\n");
                 */
                if (!(remainder == 0)) {
                    inputArabic = remainder;
                } else {
                    break;
                }
            }
            System.out.println("The given arabic number converted to roman: " + romanNum + "\n");
        }
    }

//**************Method to check the working of the converter**************
    static void checkConverter() {

        System.out.println("\n" + "*****************Check the converter with some numbers*****************" + "\n");

        int[] testArray = {-1, 0, 1, 4, 10, 99, 111, 494, 619, 944, 999, 1000, 1613, 1818, 2200, 2999, 3000, 3001, 3999, 4000};

        for (int num : testArray) {
            converter(num);
        }
    }
}
