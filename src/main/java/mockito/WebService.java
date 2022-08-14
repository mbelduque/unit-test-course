package mockito;

public class WebService {
    public void login(String username, String password, Callback callback) {
        if (checkLogin(username, password)) {
            callback.onSuccess("Login success");
        } else {
            callback.onError("Login failed");
        }
    }

    public boolean checkLogin(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
