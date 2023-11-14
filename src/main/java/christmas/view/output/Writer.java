package christmas.view.output;

public interface Writer {
    void printLine(String message);

    void printFormat(String format, Object... args);
}
