package christmas.view;

import static christmas.constants.Message.ASK_VISIT_DATE;
import static christmas.constants.Message.DISCOUNT_HEADER;
import static christmas.constants.Message.EVENT_BADGE;
import static christmas.constants.Message.EXPECT_PAY_HEADER;
import static christmas.constants.Message.GREETING;
import static christmas.constants.Message.MENU_FORMAT;
import static christmas.constants.Message.NONE;
import static christmas.constants.Message.PRESENT_HEADER;
import static christmas.constants.Message.TOTAL_DISCOUNT_HEADER;
import static christmas.constants.menu.Menu.CHAMPAGNE;

import christmas.constants.event.BadgeType;
import christmas.model.DiscountResult;
import christmas.model.discount.PresentDiscount;
import java.text.DecimalFormat;

public class OutputView {
    private final DecimalFormat decimalFormat;

    public OutputView() {
        decimalFormat = new DecimalFormat("#,###");
    }

    public void printGreeting() {
        System.out.println(GREETING.getMessage());
    }

    public void printAskVisitDate() {
        System.out.println(ASK_VISIT_DATE.getMessage());
    }

    public void printPresent(PresentDiscount presentDiscount) {
        System.out.println(PRESENT_HEADER.getMessage());
        if (presentDiscount.getAmount() == 0) {
            System.out.println(NONE.getMessage());
            return;
        }
        System.out.printf(MENU_FORMAT.getMessage(), CHAMPAGNE.getName(), presentDiscount.getAmount());
    }

    //TODO: DiscountResult에서 entry를 받아오는게 아쉽다... 코드의 길이를 더 줄여보자
    public void printDiscountDetails(DiscountResult discountResult) {
        StringBuilder stringBuilder = new StringBuilder(DISCOUNT_HEADER.getMessage());

        if (discountResult.isEventNotApplied()) {
            System.out.println(NONE.getMessage());
            return;
        }

        discountResult.getDiscountApplied()
                .forEach(entry -> {
                    int priceByEvent = discountResult.getDiscountPriceByEvent(entry.getKey());
                    stringBuilder.append(
                            String.format("%s: -%s원%n", entry.getKey().getDescription(),
                                    decimalFormat.format(priceByEvent))
                    );
                });
        System.out.println(stringBuilder);
    }

    public void printTotalDiscountPrice(DiscountResult discountResult) {
        System.out.println(TOTAL_DISCOUNT_HEADER.getMessage());
        System.out.printf("-%s원", decimalFormat.format(discountResult.getTotalDiscountPrice()));
    }

    public void printExpectedPrice(int orderPrice, DiscountResult discountResult) {
        System.out.println(EXPECT_PAY_HEADER.getMessage());

        //TODO: 계산하는 로직이 여기 왜 있어요.. 로직 분리하기
        int expectedPrice = orderPrice - discountResult.getTotalBenefitPrice();
        System.out.printf("%s원", decimalFormat.format(expectedPrice));
    }

    public void printEventBadge(BadgeType badgeType) {
        System.out.println(EVENT_BADGE.getMessage());
        System.out.println(badgeType.getName());
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }
}