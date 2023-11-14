package christmas.constants;

public enum ErrorCode {
    PREFIX("[ERROR] "),
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_MENU_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MENU_OVERFLOW("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.message + this.message;
    }
}
