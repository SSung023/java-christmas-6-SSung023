package christmas.constants;

public enum ErrorCode {
    PREFIX("[ERROR] "),
    NOT_INTEGER("입력한 값이 정수가 아닙니다. 다시 입력해주세요."),
    INVALID_DATE("방문 날짜가 유효하지 않습니다. 다시 입력해주세요.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.message + this.message;
    }
}
