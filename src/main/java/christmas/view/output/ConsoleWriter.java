package christmas.view.output;

public class ConsoleWriter implements Writer {
    @Override
    public void printLine(String message) {
        System.out.println(message);
    }

    @Override
    public void printFormat(String format, Object... args) {
        System.out.printf(format, args);
    }
}