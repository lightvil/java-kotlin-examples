import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  String manipulation examples
 *  Author:
 *      Sanghwan Choi(sanghwan.choi@gmail.com)
 */
public class Main {


    /**
     * Equivalent method of sb.reverse().toString().
     *
     * @param s
     * @return
     */
    public static String reverseToString(StringBuilder s) {
        StringBuilder sb = new StringBuilder();
        for(int e = s.length() - 1;e >=0;e--) {
            sb.append(s.charAt(e));
        }
        return sb.toString();
    }

    /**
     * Converts an integer to a Binary String.
     * @param value
     * @return
     */
    public static String toBin(int value) {
        StringBuilder sb = new StringBuilder();
        while(true) {
            sb.append(value % 2 == 0 ? '0' : '1');
            value = value / 2;
            if (value == 0) break;
        }
        return reverseToString(sb); // OR: return sb.reverse().toString();
    }
    /**
     * Converts a binary String to integer.
     * returns defaultValue when NumberFormatException occurs.
     * @param bin
     * @param defaultValue
     * @return
     */
    public static int parseBin(String bin, int defaultValue) {
        try {
            return parseBin(bin);
        } catch(NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * Converts a binary String to integer.
     * Preceding and trailing spaces are ignored.
     * NumberFormatException is throws if the input is null or contains characters none of '0' or '1'
     * @param bin
     * @return
     * @throws NumberFormatException
     */
    public static int parseBin(String bin) throws NumberFormatException {
        if (bin == null ) {
            throw new NumberFormatException("Input should be not null");
        } else {
            bin = bin.trim();
            if (bin.length() == 0) new NumberFormatException("Input should be not null");
        }
        int result = 0;
        for(int i = 0;i < bin.length();i++) {
            char digit = bin.charAt(i);
//            switch(bin.charAt(i)) {
//                case '0':
//                    result += (result << 1) /* OR result * 2 */;
//                    break;
//                case '1':
//                    result = (result << 1) /* OR result * 2 */ + 1;
//                    break;
//                default:
//                    throw new NumberFormatException("Number should be 0 or 1");
//            }
            switch(digit) {
                case '0': case '1':
                    int bit = digit - '0';
                    result = (result << 1) /* OR result * 2*/ + bit;
                    break;
                default:
                    throw new NumberFormatException("Number should be 0 or 1");
            }
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(String.format("INTEGER %d CONVERTED TO %s", 15, toBin(15)));
        String input = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("ENTER A BINARY NUMBER: ");
            try {
                input = reader.readLine();
                // If input  is null or empty String...
                if (input == null || input.length() ==0) {
                    continue;
                }
                // We have at least 1 character in input ie not null and not empty,
                // QUIT, EXIT WILL TERMINATE LOOP.
                if ("QE".contains(input.substring(0, 1).toUpperCase())) {
                    break;
                }
//                if (input.equalsIgnoreCase("QUIT")) {
//                    break;
//                }
                int result = parseBin(input);
                // OR: int result = parseBin(input, 0);
                System.out.println(String.format("\tINPUT %s CONVERTED TO %d", input, result));
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}