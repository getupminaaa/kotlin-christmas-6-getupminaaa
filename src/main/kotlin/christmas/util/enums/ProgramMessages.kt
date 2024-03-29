package christmas.util.enums

enum class ProgramMessages(val msg: String) {
    VISIT_DATE_PROMPT("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER_MENU_PROMPT("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    PROGRAM_START_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    EVENT_PREVIEW_MESSAGE("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU_HEADER("\n<주문 메뉴>"),
    TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT("\n<할인 전 총주문 금액>"),
    FREE_GIFT_HEADER("\n<증정 메뉴>"),
    FREE_GIFT_DETAIL("샴페인 1개"),
    DISCOUNT_DETAILS_HEADER("\n<혜택 내역>"),
    TOTAL_DISCOUNT_AMOUNT_HEADER("\n<총혜택 금액>"),
    EXPECTED_PAYMENT_AMOUNT_AFTER_DISCOUNT("\n<할인 후 예상 결제 금액>"),
    EVENT_BADGE_HEADER("\n<12월 이벤트 배지>"),
    NO_EVENT_MESSAGE("없음")
}