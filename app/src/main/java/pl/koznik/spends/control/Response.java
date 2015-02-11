package pl.koznik.spends.control;

public class Response<T> {

    private T returnObject;
    private Type type;
    private String errorMessage;

    private Response(Type type, T returnObject) {
        this.returnObject = returnObject;
        this.type = type;
    }

    private Response(Type type, String errorMessage, T returnObject) {
        this.returnObject = returnObject;
        this.type = type;
        this.errorMessage = errorMessage;
    }

    public static Response<String> OK() {
        return new Response<>(Type.SUCCESS, "OK");
    }

    public static <T> Response<T> OK(T returnObject) {
        return new Response<>(Type.SUCCESS, returnObject);
    }

    public static <T> Response<T> FAIL(String errorMessage) {
        return new Response<>(Type.FAIL, errorMessage, null);
    }

    public enum Type {
        SUCCESS, FAIL
    }

    public boolean isValid() {
        return Type.SUCCESS.equals(type);
    }

    public boolean isNotValid() {
        return Type.FAIL.equals(type);
    }

    public T getReturnObject() {
        return returnObject;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
