package advent.day3;

import advent.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class GondolaLift {

    private static final Pattern SYMBOLE_PATTERN = Pattern.compile("[^\\d.]+");

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    private GondolaLift() {

    }


    public static Result process(String input) throws IOException, URISyntaxException {
        return findSymbolsAndGetConcomitantNumbersAndProcessThem(input, findNumberAndStoreThem(input));
    }

    private static Result findSymbolsAndGetConcomitantNumbersAndProcessThem(String input, Map<Integer, List<NumberIndexes>> indexOfNumbersByLines) throws IOException, URISyntaxException {
        Set<NumberIndexes> eligibleValues = new HashSet<>();
        Map<Star, List<NumberIndexes>> closeToStars = new HashMap<>();
        AtomicInteger currentLine = new AtomicInteger(0);

        Utils.forEachLinesFromFile(input).forEach(line -> {
            SYMBOLE_PATTERN.matcher(line).results().forEach(symbol -> {
                if (currentLine.get() > 0) {
                    indexOfNumbersByLines.get(currentLine.get() - 1)
                            .forEach(numberIndexes -> ifMatchAddNumberToTheSet(symbol, numberIndexes, eligibleValues, closeToStars, currentLine.get()));
                }

                indexOfNumbersByLines.get(currentLine.get())
                        .forEach(numberIndexes -> ifMatchAddNumberToTheSet(symbol, numberIndexes, eligibleValues, closeToStars, currentLine.get()));

                if (currentLine.get() < indexOfNumbersByLines.size()) {
                    indexOfNumbersByLines.get(currentLine.get() + 1)
                            .forEach(numberIndexes -> ifMatchAddNumberToTheSet(symbol, numberIndexes, eligibleValues, closeToStars, currentLine.get()));
                }
            });

            currentLine.incrementAndGet();
        });

        return new Result(closeToStars.values().stream().filter(numberIndexes -> numberIndexes.size() > 1).map(numberIndexes -> numberIndexes.stream().map(NumberIndexes::getValue).reduce((integer, integer2) -> integer * integer2).orElseThrow()).reduce(Integer::sum).orElseThrow(), eligibleValues.stream().map(NumberIndexes::getValue).reduce(Integer::sum).orElseThrow());
    }


    private static void ifMatchAddNumberToTheSet(MatchResult symbol, NumberIndexes numberIndexes, Set<NumberIndexes> eligibleValues, Map<Star, List<NumberIndexes>> closeToStar, Integer currentLine) {
        if (numberIndexes.match(symbol.start())) {
            eligibleValues.add(numberIndexes);
            if ("*".equals(symbol.group())) {
                Star star = new Star(currentLine, symbol.start());
                if (closeToStar.get(star) != null) {
                    closeToStar.get(star).add(numberIndexes);
                } else {
                    closeToStar.put(star, new ArrayList<>(List.of(numberIndexes)));
                }
            }
        }
    }

    private static Map<Integer, List<NumberIndexes>> findNumberAndStoreThem(String input) throws IOException, URISyntaxException {
        Map<Integer, List<NumberIndexes>> indexOfNumbersByLines = new LinkedHashMap<>();
        AtomicInteger lineNumber = new AtomicInteger(0);

        Utils.forEachLinesFromFile(input).forEach(line -> {
            initLine(lineNumber.get(), indexOfNumbersByLines).addAll(NUMBER_PATTERN.matcher(line).results().map(match -> new NumberIndexes(match.start(), match.end() - 1, lineNumber.get(), Integer.valueOf(match.group()))).toList());

            lineNumber.incrementAndGet();
        });
        return indexOfNumbersByLines;
    }

    private static List<NumberIndexes> initLine(Integer lineNumber, Map<Integer, List<NumberIndexes>> indexOfNumbersByLines) {
        return indexOfNumbersByLines.put(lineNumber, new ArrayList<>());
    }

}
