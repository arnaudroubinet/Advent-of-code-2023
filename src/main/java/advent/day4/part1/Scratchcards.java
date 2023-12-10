package advent.day4.part1;

import advent.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

public class Scratchcards {

    private static final Pattern SYMBOLE_PATTERN = Pattern.compile("Card\\s+(\\d+):([^|]+)\\|([^|]+)$");


    public static Integer process(String input) throws IOException, URISyntaxException {
        return Utils.forEachLinesFromFile(input)
                .map(line -> SYMBOLE_PATTERN.matcher(line)
                        .results()
                        .map(Card::new)
                        .map(Card::cardValue)
                        .reduce(Integer::sum)
                        .orElse(0))
                .reduce(Integer::sum)
                .orElseThrow();
    }
}
