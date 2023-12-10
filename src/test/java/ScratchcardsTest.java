import advent.day4.part1.Scratchcards;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class ScratchcardsTest {
    @Test
    void processTest() throws IOException, URISyntaxException {
        var result = Scratchcards.process("day4/test");
        Assertions.assertEquals(13, result);
    }


    @Test
    void processInput() throws IOException, URISyntaxException {
        var result = Scratchcards.process("day4/input");
        Assertions.assertEquals(18653, result);
    }
}
