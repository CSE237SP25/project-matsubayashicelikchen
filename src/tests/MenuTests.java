package tests;

import bankapp.Menu;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MenuTests {
    
    @Test
    public void testMenuRun() {
        String simulatedUserInput = 
            "2\n" +
            "john\n" +
            "pass\n" +
            "John\n" +
            "Doe\n" +
            "john@example.com\n" +
            "123-456-7890\n" +
            "1\n" +
            "5\n" +
            "1000\n" +
            "6\n" +
            "200\n" +
            "12\n" +
            "3\n";
        InputStream originalIn = System.in;
        try {
            System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
            assertDoesNotThrow(() -> {
                new Menu().run();
            });
        } finally {
            System.setIn(originalIn);
        }
    }
}
