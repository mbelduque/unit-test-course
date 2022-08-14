package mockito;

public interface Callback {
    void onSuccess(String response);
    void onError(String error);
}
