package christmas.view.output;

import static christmas.constants.Message.NEW_LINE;

import java.util.stream.IntStream;

public class ConsoleWriter implements Writer {
    @Override
    public void printLine(String message) {
        System.out.println(message);
    }

    @Override
    public void printFormat(String format, Object... args) {
        System.out.printf(format, args);
    }

    @Override
    public void printNewLine(int count) {
        IntStream.range(0, count).forEach(idx -> System.out.print(NEW_LINE.getMessage()));
    }
}