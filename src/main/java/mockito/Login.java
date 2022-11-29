package mockito;

public class Login {
    public final WebService webService;
    public boolean isLoggedIn;

    public Login(WebService webService) {
        this.webService = webService;
        isLoggedIn = false;
    }

    public void doLogin() {
        webService.login("admin", "admin", new Callback() {
            @Override
            public void onSuccess(String response) {
                isLoggedIn = true;
            }

            @Override
            public void onError(String error) {
                isLoggedIn = false;
            }
        });
    }
}
