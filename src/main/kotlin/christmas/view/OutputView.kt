package christmas.view

import christmas.util.enums.EventType
import christmas.util.enums.ProgramMessages

class OutputView {
    init {
        printStartMsg()
    }

    private fun formatCurrency(price: Int) = String.format(CURRENCY_FORMAT, price)
    private fun formatNegativeCurrency(value: Int): String = "$NEGATIVE_PREFIX${formatCurrency(value)}"

    private fun printStartMsg() = println(ProgramMessages.PROGRAM_START_MESSAGE.msg)

    fun printEventMsg(date: Int) = println(ProgramMessages.EVENT_PREVIEW_MESSAGE.msg.format(date))

    fun printMenu(menuList: List<Map<String, Int>>) {
        println(ProgramMessages.ORDER_MENU_HEADER.msg)
        menuList.flatMap { it.entries }.forEach { (key, value) -> println("$key ${value}$QUANTITY_SUFFIX") }
    }

    fun printTotalPrice(totalPrice: Int) =
        println(ProgramMessages.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT.msg + "$NEW_LINE${formatCurrency(totalPrice)}")

    fun printFreeGift(applicableEvents: List<String>) {
        println(ProgramMessages.FREE_GIFT_HEADER.msg)
        if (applicableEvents.contains(EventType.FREE_GIFT.name)) println(ProgramMessages.FREE_GIFT_DETAIL.msg) else println(
            ProgramMessages.NO_EVENT_MESSAGE.msg
        )
    }

    fun printPromotionHistory(discountDetails: List<Pair<String, Int>>) {
        println(ProgramMessages.DISCOUNT_DETAILS_HEADER.msg)
        if (discountDetails.isNotEmpty()) {
            discountDetails.forEach {
                println("${it.first}$COLON_SEPARATOR ${formatNegativeCurrency(it.second)}")
            }
        } else println(ProgramMessages.NO_EVENT_MESSAGE.msg)
    }

    fun printTotalDiscount(totalDiscount: Int) =
        println(ProgramMessages.TOTAL_DISCOUNT_AMOUNT_HEADER.msg + "$NEW_LINE${formatNegativeCurrency(totalDiscount)}")

    fun printFinalPayment(finalPayment: Int) =
        println(ProgramMessages.EXPECTED_PAYMENT_AMOUNT_AFTER_DISCOUNT.msg + "$NEW_LINE${formatCurrency(finalPayment)}")

    fun printEventBadge(eventBadge: String) {
        println(ProgramMessages.EVENT_BADGE_HEADER.msg)
        if (EventType.BADGE_EVENT.promotionName.contains(eventBadge)) {
            println(eventBadge)
        } else println(ProgramMessages.NO_EVENT_MESSAGE.msg)
    }

    companion object {
        private const val NEGATIVE_PREFIX = "-"
        private const val NEW_LINE = "\n"
        private const val CURRENCY_FORMAT = "%,d원"
        private const val QUANTITY_SUFFIX = "개"
        private const val COLON_SEPARATOR = ":"
    }
}
