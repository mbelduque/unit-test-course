package mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddMock1Test {

    private Add add;
    private ValidNumber validNumber;

    @BeforeEach
    void setUp() {
        // 1: Set up the mock on BeforeEach
        validNumber = Mockito.mock(ValidNumber.class);
        add = new Add(validNumber);
    }

    @AfterEach
    void tearDown() {
        validNumber = null;
        add = null;
    }

    @Test
    void addTest() {
        Mockito.when(validNumber.check(3)).thenReturn(true);
        Mockito.when(validNumber.check(2)).thenReturn(true);
        assertEquals(5, add.add(3, 2));
    }
}