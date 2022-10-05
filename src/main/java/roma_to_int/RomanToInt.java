package roma_to_int;

/**
 * Created on 05.10.2022
 * @author Mykola Horkov
 * <br> mykola.horkov@gmail.com
 * <br>Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <br>Symbol -> Value
 * <br>I  -> 1
 * <br>V  -> 5
 * <br>X  -> 10
 * <br>L  -> 50
 * <br>C  -> 100
 * <br>D  -> 500
 * <br>M  -> 1000
 * <br>For example, 2 is written as II in Roman numeral, just two ones added together.
 * <br>12 is written as XII, which is simply X + II.
 * <br>The number 27 is written as XXVII, which is XX + V + II.
 *
 * <br>Roman numerals are usually written largest to smallest from left to right.
 * <br>However, the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four. The same principle applies to the number nine,
 * which is written as IX. There are six instances where subtraction is used:
 *
 * <br>I can be placed before V (5) and X (10) to make 4 and 9.
 * <br>X can be placed before L (50) and C (100) to make 40 and 90.
 * <br>C can be placed before D (500) and M (1000) to make 400 and 900.
 * <br>Given a roman numeral, convert it to an integer.
 */
public class RomanToInt {

    /**
     * Collect Integer representation based on value and place of each Roman Number string.
     */
    public static int romanToInt(String s) {
        int result = 0;

        // Constraint: 1 <= s.length <= 15
        if(1 > s.length() || s.length() > 15){
            return result;
        }
        // loop which goes through the given string till excluding last element.
        for(int i = 0; i < s.length() - 1; i++){
            int currentScore = getScoreByChar(s.charAt(i)); // get score of the current element
            // compare score of current element with the score of the next element
            if(currentScore < getScoreByChar(s.charAt(i + 1))){
                // extract current score from result if current element is less then next one
                result -= currentScore;
            } else {
                // otherwise add current score to result
                result += currentScore;
            }
        }
        // add the score of the last element in given string
        result += getScoreByChar(s.charAt(s.length() - 1));
        return result;
    }

    /**
     * Return Integer representation of given Roman character.
     */
    private static int getScoreByChar(char c){
        switch(c){
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
