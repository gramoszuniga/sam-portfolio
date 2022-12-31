package samportfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author : Gonzalo Ramos Zúñiga
 * @since : 2022-12-30
 **/
public class ApplicationTest {

    @Test
    public void handleRequest_shouldReturnConstantValue() {
        Application function = new Application();
        Object result = function.handleRequest("echo", null);
        assertEquals("echo", result);
    }

}