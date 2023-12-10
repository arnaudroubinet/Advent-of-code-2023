import advent.day2.CubeConundrum;
import advent.day2.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class CubeConundrumTest {


    @Test
    void processWithTestInput() throws IOException, URISyntaxException {
        Result result = CubeConundrum.process("day2/test");
        Assertions.assertEquals(8, result.gameNumber);
        Assertions.assertEquals(2286, result.power);
    }

    @Test
    void processWithRealInput() throws IOException, URISyntaxException {
        Result result = CubeConundrum.process("day2/input");
        Assertions.assertEquals(2447, result.gameNumber);
        Assertions.assertEquals(56322, result.power);
    }
}
