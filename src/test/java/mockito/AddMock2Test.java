package mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class AddMock2Test {
    // 2: Set up the mock with annotations and BeforeEach
    @InjectMocks
    private Add add;
    @Mock
    private ValidNumber validNumber;
    @Mock
    private Print print;
    @Captor
    private ArgumentCaptor<Integer> captor;
    @Spy
    final List<String> spyList = new ArrayList<>();
    @Mock
    final List<String> mockList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        add = null;
        validNumber = null;
        print = null;
        captor = null;
    }

    @Test
    void addTest() {
        // Mock behavior
        Mockito.when(validNumber.check(3)).thenReturn(true);
        boolean checkNumberTrue = validNumber.check(3);
        assertTrue(checkNumberTrue);

        Mockito.when(validNumber.check("a")).thenReturn(false);
        boolean checkNumberFalse = validNumber.check("a");
        assertFalse(checkNumberFalse);
    }

    @Test
    void addMockExceptionTest() {
        Mockito.when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("Zero is not allowed"));
        assertThrows(ArithmeticException.class, () -> validNumber.checkZero(0));
    }

    @Test
    void addRealMethodTest() {
        // Mock with real method
        Mockito.when(validNumber.check(3)).thenCallRealMethod();
        assertTrue(validNumber.check(3));

        Mockito.when(validNumber.check("a")).thenCallRealMethod();
        assertFalse(validNumber.check("a"));
    }

    @Test
    void addDoubleToIntTest() {
        Answer<Integer> answer = invocationOnMock -> 7;
        Mockito.when(validNumber.doubleToInt(7.7)).thenAnswer(answer);
        assertEquals(14, add.addDoubleToInt(7.7));
    }

    @Test
    void patternTest() {
        // Given
        Mockito.when(validNumber.check(4)).thenReturn(true);
        Mockito.when(validNumber.check(5)).thenReturn(true);
        // When
        int result = add.add(4, 5);
        // Then
        assertEquals(9, result);
    }

    @Test
    void patternBDDTest() {
        // Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        // When
        int result = add.add(4, 5);
        // Then
        assertEquals(9, result);
    }

    @Test
    void argumentMatcherTest() {
        // Given
        given(validNumber.check(anyInt())).willReturn(true);
        // When
        int result = add.add(4, 5);
        // Then
        assertEquals(9, result);
    }

    @Test
    void addPrintTest() {
        // Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        // When
        add.addPrint(4, 5);
        // Then
        verify(validNumber).check(4);
        verify(print).showMessage(9);
        verify(print, never()).showError();
        //verify(validNumber, times(2)).check(4);
        //verify(validNumber, never()).check(99);
        //verify(validNumber, atLeast(1)).check(4);
        //verify(validNumber, atMost(3)).check(4);
    }

    @Test
    void addPrintCaptorTest() {
        // Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        // When
        add.addPrint(4, 5);
        // Then
        verify(validNumber).check(4);
        verify(print).showMessage(captor.capture());
        assertEquals(captor.getValue().intValue(), 9);
    }

    @Test
    void spyTest() {
        spyList.add("a");
        spyList.add("b");
        verify(spyList).add("a");
        verify(spyList).add("b");
        assertEquals(2, spyList.size());
    }

    @Test
    void mockTest() {
        mockList.add("a");
        mockList.add("b");
        verify(mockList).add("a");
        verify(mockList).add("b");
        given(mockList.size()).willReturn(2);
        assertEquals(2, mockList.size());
    }
}
