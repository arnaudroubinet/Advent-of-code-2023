package advent.day2;

import advent.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static advent.day2.Color.*;

public class CubeConundrum {

    private static final Pattern GAME_PATTERN = Pattern.compile("Game (\\d+):");
    private static final Pattern COLOR_PATTERN = Pattern.compile("(\\d+) (blue|red|green)");
    private static final Map<Color, Integer> MAX_BY_COLOR = Map.of(red, 12, green, 13, blue, 14);

    private CubeConundrum() {

    }

    public static Result process(String input) throws IOException, URISyntaxException {
        return Utils.forEachLinesFromFile(input)
                .map(CubeConundrum::printLine)
                .map(line -> {
                    Map<Color, Integer> numberByCube = extractNumberOfCubesByColor(line);
                    return Result.builder()
                            .gameNumber(checkIfTheGameIsReal(numberByCube) ?  extractGameNumber(line) : 0)
                            .power(processPower(numberByCube))
                            .build();
                }).reduce(Result::merge)
                .orElseThrow();

    }

    private static Integer processPower(Map<Color, Integer> numberByCube) {
        return numberByCube.values().stream().reduce((integer, integer2) -> integer * integer2).orElseThrow();
    }

    private static boolean checkIfTheGameIsReal(Map<Color, Integer> numberByCube) {
        boolean result = numberByCube.entrySet()
                .stream()
                .map(CubeConundrum::isColorNumberSuperiorToMaximum)
                .filter(CubeConundrum::filterTrueValues)
                .findFirst()
                .orElse(true);
        if (result)
            System.out.println("Game is valid");
        else
            System.out.println("Game is invalid");

        return result;
    }

    private static Map<Color, Integer> extractNumberOfCubesByColor(String line) {
        Map<Color, Integer> numberByColors = new HashMap<>();

        Matcher matcher = COLOR_PATTERN.matcher(line);

        matcher.results().forEach(matchResult -> {
            Color key = valueOf(matchResult.group(2));
            if (numberByColors.get(key) == null) {
                numberByColors.put(key, Integer.valueOf(matchResult.group(1)));
            } else {
                numberByColors.put(key, Math.max(numberByColors.get(key), Integer.parseInt(matchResult.group(1))));
            }
        });


        return numberByColors;
    }

    private static Integer extractGameNumber(String line) {
        Matcher matcher = GAME_PATTERN.matcher(line);
        if (!matcher.find()) throw new RuntimeException("No match found");
        return Integer.valueOf(matcher.group(1));
    }

    private static String printLine(String line){
        System.out.println(line);
        return line;
    }

    private static Boolean isColorNumberSuperiorToMaximum(Map.Entry<Color, Integer> colorIntegerEntry) {
        boolean result = MAX_BY_COLOR.get(colorIntegerEntry.getKey()) >= colorIntegerEntry.getValue();
        if (result)
            System.out.println("With " + colorIntegerEntry.getValue() + " the color " + colorIntegerEntry.getKey() + " is eligible (max value is " + MAX_BY_COLOR.get(colorIntegerEntry.getKey()) + ").");
        else
            System.out.println("With " + colorIntegerEntry.getValue() + " the color " + colorIntegerEntry.getKey() + " is not eligible (max value is " + MAX_BY_COLOR.get(colorIntegerEntry.getKey()) + ").");

        return result;
    }

    private static boolean filterTrueValues(Boolean aBoolean) {
        return !aBoolean;
    }
}
