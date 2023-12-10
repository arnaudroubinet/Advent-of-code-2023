import advent.day4.part2.ScratchcardsPart2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class ScratchcardsPart2Test {
    @Test
    void processTest() throws IOException, URISyntaxException {
        var result = ScratchcardsPart2.process("day4/test");
        Assertions.assertEquals(30, result);
    }


    @Test
    void processInput() throws IOException, URISyntaxException {
        var result = ScratchcardsPart2.process("day4/input");
        Assertions.assertEquals(5921508, result);
    }
}
