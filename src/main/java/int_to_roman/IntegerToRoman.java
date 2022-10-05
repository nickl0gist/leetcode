package int_to_roman;

/**
 * Created on 05.10.2022
 *
 * @author Mykola Horkov
 * <br> mykola.horkov@gmail.com
 * <br> LeetCode:
 * "Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol -> Value
 * <br>I  -> 1
 * <br>V  -> 5
 * <br>X  -> 10
 * <br>L  -> 50
 * <br>C  -> 100
 * <br>D  -> 500
 * <br>M  -> 1000
 * <br>For example, 2 is written as II in Roman numeral, just two one's added together.
 * <br>The number 12 is written as XII, which is simply X + II.
 * <br>The number 27 is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four. The same principle applies to the number nine,
 * which is written as IX. There are six instances where subtraction is used:
 * <p>
 * <br>I can be placed before V (5) and X (10) to make 4 and 9.
 * <br>X can be placed before L (50) and C (100) to make 40 and 90.
 * <br>C can be placed before D (500) and M (1000) to make 400 and 900.
 * <br>Given an integer, convert it to a roman numeral.
 * <br>Example 1:
 * <br>Input: num = 3
 * <br>Output: "III"
 * <br>Explanation: 3 is represented as 3 ones."
 */
public class IntegerToRoman {

    public static String intToRoman(int num) {

        String str = "";
        //Constraints: 1 <= num <= 3999
        if (num < 1 || num > 3999) {
            return str;
        }

        // Get number of thousands in group.
        int thousands = num / 1000;
        if (thousands > 0) {
            str += getRomanThousand(thousands);
        }

        // Get number of hundreds in group.
        int hundreds = (num % 1000) / 100;
        if (hundreds > 0) {
            str += getRomanHundreds(hundreds);
        }

        // Get number of tens in group.
        int tens = (num % 100) / 10;
        if (tens > 0) {
            str += getRomanTens(tens);
        }

        // Get number of ones in group.
        int ones = num % 10;
        if (ones > 0) {
            str += getRomanOnes(ones);
        }

        return str;
    }

    /**
     * Due to constrains the maximum thousands is 3. So in Roman ints it could be three possible values M, MM, MMM
     */
    private static String getRomanThousand(int thousands) {
        String res = "";
        for (int i = 1; i <= thousands; i++) {
            res += "M";
        }
        return res;
    }

    /**
     * Each group consists of combination of Character which represents:
     * <br>1 in this group ('C' for hundreds),
     * <br>5 in this group ('D' for hundreds),
     * <br>and 1 for the next upper group, i.e. 'M' represents 1 for thousands which is next higher level for hundreds.
     */
    private static String getRomanHundreds(int hundreds) {
        return getRomanNum(hundreds, "C", "D", "M");
    }

    /**
     * Each group consists of combination of Character which represents:
     * <br>1 in this group ('X' for tens),
     * <br>5 in this group ('L' for tens),
     * <br>and 1 for the next upper group, i.e. 'C' represents 1 for hundreds which is next higher level for tens.
     */
    private static String getRomanTens(int tens) {
        return getRomanNum(tens, "X", "L", "C");
    }

    /**
     * Each group consists of combination of Character which represents:
     * <br>1 in this group ('I' for ones),
     * <br>5 in this group ('V' for ones),
     * <br>and 1 for the next upper group, i.e. 'X' represents 1 for tens which is next higher level for ones.
     */
    private static String getRomanOnes(int ones) {
        return getRomanNum(ones, "I", "V", "X");
    }

    /**
     * Return Roman interpretation based on <code>num</code> integer value. And given values of '1', '5', and '10'.
     * There are all possible combinations in switch/case.
     * @param num simple integer to be converted.
     * @param charOne Roman interpretation of '1' in current group.
     * @param charFive Roman interpretation of '5' in current group.
     * @param charTen Roman interpretation of '10' in current group.
     * @return String representation of <code>num</code>.
     */
    private static String getRomanNum(int num, String charOne, String charFive, String charTen) {
        switch (num) {
            case 1:
                return charOne;
            case 2:
                return charOne + charOne;
            case 3:
                return charOne + charOne + charOne;
            case 4:
                return charOne + charFive;
            case 5:
                return charFive;
            case 6:
                return charFive + charOne;
            case 7:
                return charFive + charOne + charOne;
            case 8:
                return charFive + charOne + charOne + charOne;
            case 9:
                return charOne + charTen;
            default:
                return "";
        }
    }
}
