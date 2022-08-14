package mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidNumberTest {

    private ValidNumber validNumber;

    @BeforeEach
    void setUp() {
        validNumber = new ValidNumber();
    }

    @AfterEach
    void tearDown() {
        validNumber = null;
    }

    @Test
    void checkTest() {
        assertTrue(validNumber.check(2));
    }

    @Test
    void checkNegativeTest() {
        assertFalse(validNumber.check(-5));
    }

    @Test
    void checkStringTest() {
        assertFalse(validNumber.check("a"));
    }

    @Test
    void checkZeroTest() {
        assertTrue(validNumber.checkZero(5));
    }

    @Test
    void checkZeroStringTest() {
        assertFalse(validNumber.checkZero("a"));
    }

    @Test
    void checkZeroExceptionTest() {
        assertThrows(ArithmeticException.class, () -> validNumber.checkZero(0));
    }

    @Test
    void doubleToIntTest() {
        assertEquals(5, validNumber.doubleToInt(5.555));
    }

    @Test
    void doubleToIntStringTest() {
        assertEquals(0, validNumber.doubleToInt("a"));
    }
}