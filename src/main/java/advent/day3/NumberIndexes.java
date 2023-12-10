package advent.day3;


import java.util.Objects;

public class NumberIndexes {
    private final Integer start;
    private final Integer end;
    private final Integer line;
    private final Integer value;

    NumberIndexes(Integer start, Integer end, Integer line, Integer value) {
        this.start = start;
        this.end = end;
        this.line = line;
        this.value = value;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getValue() {
        return value;
    }

    public boolean match(int index) {
        return index >= this.start - 1 && index <= this.end + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberIndexes that = (NumberIndexes) o;
        return Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(line, that.line) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, line, value);
    }
}
