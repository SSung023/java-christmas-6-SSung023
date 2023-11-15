package christmas.exception;

import christmas.view.output.OutputView;
import java.util.Arrays;
import java.util.function.Supplier;

public class RetryHandler implements ExceptionHandler {
    private final OutputView outputView;

    public RetryHandler(OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    @SafeVarargs
    public final <T> T execute(Supplier<T> action, Class<? extends Exception>... exceptions) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                printException(e, exceptions);
            }
        }
    }

    private void printException(Exception actual, Class<? extends Exception>... exceptions) {
        if (isExpectedException(actual, exceptions)) {
            outputView.printError(actual.getMessage());
            return;
        }
        throw new RuntimeException(actual);
    }

    private boolean isExpectedException(Exception actual, Class<? extends Exception>... exceptions) {
        return Arrays.stream(exceptions)
                .anyMatch(exception -> exception.isInstance(actual));
    }
}
