import advent.day3.GondolaLift;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class GondolaLiftTest {

    @Test
    void processTest() throws IOException, URISyntaxException {
        var result = GondolaLift.process("day3/test");
        Assertions.assertEquals(4361, result.numbersSum());
        Assertions.assertEquals(467835, result.gearSum());
    }


    @Test
    void processInput() throws IOException, URISyntaxException {
        var result = GondolaLift.process("day3/input");
        Assertions.assertEquals(537732, result.numbersSum());
        Assertions.assertEquals(84883664, result.gearSum());
    }


}
