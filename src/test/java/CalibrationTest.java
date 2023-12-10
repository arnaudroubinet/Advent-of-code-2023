import advent.day1.Calibration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class CalibrationTest {

    @Test
    void process() throws IOException, URISyntaxException {
        Assertions.assertEquals(281L,Calibration.process("day1/test"));
    }
}
