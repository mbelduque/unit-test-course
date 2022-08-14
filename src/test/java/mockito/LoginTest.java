package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class LoginTest {

    @InjectMocks
    private Login login;

    @Mock
    private WebService webService;

    @Captor
    private ArgumentCaptor<Callback> callbackArgumentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void doLogin() {
        doAnswer(invocation -> {
            ((Callback) invocation.getArguments()[2]).onSuccess("Login success");
            return null;
        }).when(webService).login(anyString(), anyString(), any(Callback.class));
        login.doLogin();
        verify(webService, times(1))
                .login(anyString(), anyString(), any(Callback.class));
        assertTrue(login.isLoggedIn);
    }

    @Test
    void doLoginError() {
        doAnswer(invocation -> {
            ((Callback) invocation.getArguments()[2]).onError("Login failed");
            return null;
        }).when(webService).login(anyString(), anyString(), any(Callback.class));
        login.doLogin();
        verify(webService, times(1))
                .login(anyString(), anyString(), any(Callback.class));
        assertFalse(login.isLoggedIn);
    }

    @Test
    void doLoginCaptorTest() {
        login.doLogin();
        verify(webService, times(1))
                .login(anyString(), anyString(), callbackArgumentCaptor.capture());
        callbackArgumentCaptor.getValue().onSuccess("Login success");
        assertTrue(login.isLoggedIn);
    }

    @Test
    void doLoginCaptorErrorTest() {
        login.doLogin();
        verify(webService, times(1))
                .login(anyString(), anyString(), callbackArgumentCaptor.capture());
        callbackArgumentCaptor.getValue().onError("Login failed");
        assertFalse(login.isLoggedIn);
    }
}