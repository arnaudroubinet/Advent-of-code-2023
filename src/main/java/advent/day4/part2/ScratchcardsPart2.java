package advent.day4.part2;

import advent.day4.part1.Card;
import advent.utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class ScratchcardsPart2 {

    private static final Pattern SYMBOLE_PATTERN = Pattern.compile("Card\\s+(\\d+):([^|]+)\\|([^|]+)$");


    public static Integer process(String input) throws IOException, URISyntaxException {
        final AtomicInteger lineCounter = new AtomicInteger(0);
        final Map<Integer, Integer> values = new HashMap<>();

        for(int i = 0 ; i < Utils.countLinesFromFile(input);i++){
            values.put(i,1);
        }

         Utils.forEachLinesFromFile(input)
                .forEach(line -> {
                    final int currentLine = lineCounter.getAndIncrement();

                    int nbOccurrences = Optional.ofNullable(values.get(currentLine)).orElse(1);

                    for( int i = 0 ; i < nbOccurrences ; i++){
                    SYMBOLE_PATTERN.matcher(line)
                            .results()
                            .map(Card::new)
                            .map(Card::nbCardWin)
                            .forEach(nbCardWin -> incrementCounter(currentLine, nbCardWin, values));
                    }
                });

         System.out.println(values);

        return values.values().stream().reduce(Integer::sum).orElseThrow();

    }

    private static void incrementCounter(int currentLine, Integer nbWin, Map<Integer, Integer> values) {
        for(int i = currentLine+1 ; i < currentLine+1 + nbWin ; i++){
            values.put(i,values.get(i)+1);

        }
    }
}
