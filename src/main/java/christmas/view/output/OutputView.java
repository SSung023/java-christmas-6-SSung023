package christmas.view.output;

import static christmas.constants.Message.ASK_MENU;
import static christmas.constants.Message.ASK_VISIT_DATE;
import static christmas.constants.Message.DISCOUNT_FORMAT;
import static christmas.constants.Message.DISCOUNT_HEADER;
import static christmas.constants.Message.EVENT_BADGE;
import static christmas.constants.Message.EXPECT_PAY_HEADER;
import static christmas.constants.Message.GREETING;
import static christmas.constants.Message.MENU_FORMAT;
import static christmas.constants.Message.NEW_LINE;
import static christmas.constants.Message.NONE;
import static christmas.constants.Message.ORDER_HEADER;
import static christmas.constants.Message.PRESENT_HEADER;
import static christmas.constants.Message.PREVIEW_HEADER;
import static christmas.constants.Message.PRICE_FORMAT;
import static christmas.constants.Message.TOTAL_DISCOUNT_HEADER;
import static christmas.constants.Message.TOTAL_HEADER;
import static christmas.constants.menu.Menu.CHAMPAGNE;

import christmas.constants.event.BadgeType;
import christmas.constants.event.EventType;
import christmas.constants.menu.Menu;
import christmas.model.EventResult;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private final Writer writer;
    private final DecimalFormat decimalFormat;

    public OutputView(Writer writer) {
        this.writer = writer;
        decimalFormat = new DecimalFormat("#,###");
    }

    public void printGreeting() {
        writer.printLine(GREETING.getMessage());
    }

    public void printAskVisitDate() {
        writer.printLine(ASK_VISIT_DATE.getMessage());
    }

    public void printAskMenu() {
        writer.printLine(ASK_MENU.getMessage());
    }

    public void printPreview(int date) {
        writer.printFormat(PREVIEW_HEADER.getMessage(), date);
        writer.printNewLine(2);
    }

    public void printOrderMenu(Map<Menu, Integer> menuScript) {
        StringBuilder stringBuilder = new StringBuilder(ORDER_HEADER.getMessage())
                .append(NEW_LINE.getMessage());
        menuScript.forEach(
                (menu, amount) -> stringBuilder.append(String.format(MENU_FORMAT.getMessage(), menu.getName(), amount))
        );
        writer.printLine(stringBuilder.toString());
    }

    public void printBeforeDiscountPrice(int orderPrice) {
        writer.printLine(TOTAL_HEADER.getMessage());
        writer.printFormat(PRICE_FORMAT.getMessage(), decimalFormat.format(orderPrice));
        writer.printNewLine(2);
    }

    public void printPresent(int discountPrice) {
        StringBuilder stringBuilder = new StringBuilder(PRESENT_HEADER.getMessage())
                .append(NEW_LINE.getMessage());

        if (discountPrice == 0) {
            stringBuilder.append(NONE.getMessage())
                    .append(NEW_LINE.getMessage());
            writer.printLine(stringBuilder.toString());
            return;
        }
        stringBuilder.append(String.format(MENU_FORMAT.getMessage(), CHAMPAGNE.getName(), 1));
        writer.printLine(stringBuilder.toString());
    }

    public void printEventDetails(EventResult eventResult) {
        StringBuilder stringBuilder = new StringBuilder(DISCOUNT_HEADER.getMessage())
                .append(NEW_LINE.getMessage());

        if (eventResult.isEventNotApplied()) {
            stringBuilder.append(NONE.getMessage())
                    .append(NEW_LINE.getMessage());
            writer.printLine(stringBuilder.toString());
            return;
        }

        eventResult.getDiscountApplied()
                .forEach(entry -> stringBuilder.append(getDetailPerDiscount(eventResult, entry.getKey())));
        writer.printLine(stringBuilder.toString());
    }

    private String getDetailPerDiscount(EventResult eventResult, EventType eventType) {
        int priceByDiscount = eventResult.getDiscountPriceByEvent(eventType);
        return String.format(DISCOUNT_FORMAT.getMessage(), eventType.getDescription(),
                decimalFormat.format(priceByDiscount));
    }

    public void printTotalBenefitPrice(int totalBenefitPrice) {
        StringBuilder stringBuilder = new StringBuilder(TOTAL_DISCOUNT_HEADER.getMessage())
                .append(NEW_LINE.getMessage());
        if (totalBenefitPrice != 0) {
            stringBuilder.append("-");
        }

        stringBuilder.append(
                String.format(PRICE_FORMAT.getMessage(), decimalFormat.format(totalBenefitPrice))
        );
        writer.printFormat(stringBuilder.toString());
        writer.printNewLine(2);
    }

    public void printExpectedPrice(int expectedPrice) {
        writer.printLine(EXPECT_PAY_HEADER.getMessage());
        writer.printFormat(PRICE_FORMAT.getMessage(), decimalFormat.format(expectedPrice));
        writer.printNewLine(2);
    }

    public void printEventBadge(BadgeType badgeType) {
        writer.printLine(EVENT_BADGE.getMessage());
        writer.printLine(badgeType.getName());
    }

    public void printError(String errorMessage) {
        writer.printLine(errorMessage);
    }
}