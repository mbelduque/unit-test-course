package mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class WebServiceTest {
    private WebService webService;

    @Mock
    private Callback callback;

    @BeforeEach
    void setUp() {
        webService = new WebService();
    }

    @AfterEach
    void tearDown() {
        webService = null;
    }

    @Test
    void checkLoginTest() {
        assertTrue(webService.checkLogin("admin", "admin"));
    }

    @Test
    void checkLoginErrorTest() {
        assertFalse(webService.checkLogin("admin", "admin1"));
    }

    @Test
    void loginTest() {
        // Given
        callback = mock(Callback.class);
        // When
        webService.login("admin", "admin", callback);
        // Then
        verify(callback).onSuccess("Login success");
    }

    @Test
    void loginErrorTest() {
        // Given
        callback = mock(Callback.class);
        // When
        webService.login("admin", "admin1", callback);
        // Then
        verify(callback).onError("Login failed");
    }
}