package advent.day1;

import advent.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Calibration {

    private Calibration(){

    }

    private static final Map<String, Long> STRING_VALUE_TO_INTEGER = Map.of("one", 1L, "two", 2L, "three", 3L, "four", 4L, "five", 5L, "six", 6L, "seven", 7L, "eight", 8L, "nine", 9L);
    private static final Pattern NUMBERS_PATTERN = Pattern.compile("(?=(one|two|three|four|five|six|seven|eight|nine|\\d))");

    public static Long process(String input) throws IOException, URISyntaxException {
        return Utils.forEachLinesFromFile(input)
                .map(
                        text -> NUMBERS_PATTERN.matcher(printAndReturnOriginalString(text))
                                .results()
                                .map(Calibration::convertToDigit)
                                .reduce(Calibration::concatNumbers)
                                .map(Calibration::printAndReturnCleanupString)
                                .map(Calibration::extractAndConcatFirstAndLastDigits)
                                .map(Calibration::printAndReturnTheNumber)
                                .orElseThrow(() -> new RuntimeException("Don't find digits into the string : " + text))
                ).reduce(Long::sum)
                .map(Calibration::printAndReturnFinalResult)
                .orElseThrow(() -> new RuntimeException("Error occur during the total calculation"));

    }

    private static Long concatNumbers(Long long1, Long long2) {
        return Long.valueOf(long1 + "" + long2);
    }


    private static Long extractAndConcatFirstAndLastDigits(Long numbers) {
        return Long.parseLong("" + numbers.toString().toCharArray()[0] + numbers.toString().toCharArray()[numbers.toString().length() - 1]);
    }

    private static String printAndReturnOriginalString(String value) {
        System.out.println("Original String \t\t:\t " + value);
        return value;
    }

    private static Long printAndReturnCleanupString(Long value) {
        System.out.println("Cleanup String \t\t\t:\t " + value);
        return value;
    }

    private static Long printAndReturnFinalResult(Long value) {
        System.out.println("Total : " + value);
        return value;
    }

    private static Long printAndReturnTheNumber(Long value) {
        System.out.println("First and last numbers \t:\t " + value);
        System.out.println("----------------------------------");
        return value;
    }

    private static Long convertToDigit(MatchResult value) {
        try {
            return Long.valueOf(value.group(1));
        } catch (NumberFormatException e) {
            return STRING_VALUE_TO_INTEGER.get(value.group(1));
        }
    }
}
