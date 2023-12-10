package advent.day4.part1;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public record Card(String id, List<String> numbers, List<String> winningNumbers) {

    private static final Pattern SYMBOLE_PATTERN = Pattern.compile("(\\d+)+");

    public Card(MatchResult matchResult) {
        this(
                matchResult.group(1),
                SYMBOLE_PATTERN.matcher(matchResult.group(2)).results().map(MatchResult::group).toList(),
                SYMBOLE_PATTERN.matcher(matchResult.group(3)).results().map(MatchResult::group).toList()
         );

    }

    public Integer cardValue() {
        long nb = numbers.stream().filter(winningNumbers::contains).map(Integer::valueOf).count();
        return nb == 0 ? 0 : Double.valueOf(Math.pow(Long.valueOf(2).doubleValue(), Long.valueOf(nb).doubleValue()-1)).intValue();
    }

    public int nbCardWin() {
        return (int) numbers.stream().filter(winningNumbers::contains).map(Integer::valueOf).count();
    }
}
