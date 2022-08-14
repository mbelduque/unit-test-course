package junit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.fail;

class CalculatorTest {

    private Calculator calculator;
    private static Calculator calculatorStatic;

    @BeforeAll
    static void beforeAll() {
        calculatorStatic = new Calculator();
    }

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    @AfterAll
    static void afterAll() {
        calculatorStatic = null;
    }

    @Test
    void calculatorNotNullTest() {
        assertNotNull(calculator);
        assertNotNull(calculatorStatic);
    }

    @Test
    void addTwoNumbersTest() {
        assertEquals(30, calculator.add(10, 20));
    }

    @Test
    void subtractTwoNumbersTest() {
        assertEquals(-10, calculator.subtract(10, 20));
    }

    @Test
    void multiplyTwoNumbersTest() {
        assertEquals(200, calculator.multiply(10, 20));
    }

    @Test
    void divideTwoNumbersTest() {
        assertEquals(2, calculator.divide(10, 5));
    }

    @Test
    @Disabled("Disabled test until resolve ethe issue")
    @DisplayName("divideByZeroTest -> doesn't work")
    void divideByZeroTest() {
        fail("Fail detected manually");
        assertEquals(0, calculator.divideByZero(10, 0));
    }

    @Test
    void divideByZeroThrowExceptionTest() {
        assertThrows(ArithmeticException.class, () -> calculator.divideByZero(10, 0));
    }

    // If some asserts fail, the others will be executed equally
    @Test
    void addAssertAllTest() {
        assertAll(
                () -> assertEquals(30, calculator.add(10, 20)),
                () -> assertEquals(40, calculator.add(10, 30)),
                () -> assertEquals(50, calculator.add(10, 40)),
                () -> assertEquals(60, calculator.add(10, 50))
        );
    }

    // Used for testing method with nested tests
    @Nested
    class AddGroupTest {
        @Test
        void addPositiveNumbersTest() {
            assertEquals(30, calculator.add(15, 15));
        }

        @Test
        void addNegativeNumbersTest() {
            assertEquals(-30, calculator.add(-15, -15));
        }

        @Test
        void addZeroTest() {
            assertEquals(0, calculator.add(0, 0));
        }
    }

    // Used for testing method with parameterized tests
    @ParameterizedTest(name = "{index} => a={0}, b={1}, expected={2}")
    @MethodSource("addProviderData")
    void addParametrizedTest(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    // Used for providing data for parameterized tests
    static Stream<Arguments> addProviderData() {
        return Stream.of(
                Arguments.of(10, 20, 30),
                Arguments.of(10, 30, 40),
                Arguments.of(10, 40, 50),
                Arguments.of(10, 50, 60)
        );
    }

    @Test
    void timeoutTest() {
        assertTimeout(
                java.time.Duration.ofMillis(1500),
                () -> calculator.longRunningMethod()
        );
    }
}