package junit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TempConverterTest {
    private TempConverter tempConverter;

    @BeforeEach
    void setUp() {
        tempConverter = new TempConverter();
    }

    @AfterEach
    void tearDown() {
        tempConverter = null;
    }

    @Nested
    class CelsiusToFahrenheit {
        @Test
        void positiveCelsiusToFahrenheitTest() {
            assertEquals(50, tempConverter.celsiusToFahrenheit(10));
        }

        @Test
        void negativeCelsiusToFahrenheitTest() {
            assertEquals(-4, tempConverter.celsiusToFahrenheit(-20));
        }
    }
}
