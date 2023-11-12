package christmas.constants;

public enum ErrorCode {
    PREFIX("[ERROR] ")
    ;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.message + this.message;
    }
}
