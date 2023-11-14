package christmas.model

import christmas.model.data.MenuItem
import christmas.model.data.OrderForm
import christmas.util.enums.EventType
import christmas.util.enums.MenuBoard
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DiscountCalculatorTest {
    private val applicableEvents = listOf("D_DAY", "STAR_DAY", "WEEKDAYS", "FREE_GIFT")

    private val orderForms = listOf(
        OrderForm(MenuItem(MenuBoard.APPETIZER.name, "타파스", 5500), 1),
        OrderForm(MenuItem(MenuBoard.DESSERT.name, "초코케이크", 15000), 2),
        OrderForm(MenuItem(MenuBoard.BEVERAGE.name, "레드와인", 60000), 1),
        OrderForm(MenuItem(MenuBoard.MAIN_DISH.name, "해산물파스타", 35000), 1),
        OrderForm(MenuItem(MenuBoard.MAIN_DISH.name, "바비큐립", 54000), 3),
        OrderForm(MenuItem(MenuBoard.APPETIZER.name, "시저샐러드", 8000), 1)
    )

    @ParameterizedTest
    @CsvSource(value = ["2,31246", "9,31946", "20,33046", "24,33446"], delimiter = ',')
    @DisplayName("할인금액을 정상적으로 계산하는지 테스트")
    fun doDiscountTest(dDayEventDate: Int, totalDiscount: Int) {
        val discountCalculator = DiscountCalculator(dDayEventDate, orderForms)
        discountCalculator.doDiscount(applicableEvents)
        assertEquals(totalDiscount, discountCalculator.totalDiscount)
    }

    @ParameterizedTest
    @CsvSource(value = ["2,120000,113754", "9,140000,133054", "20,140000,131954", "24,140000,131554"], delimiter = ',')
    @DisplayName("최종 금액을 정상적으로 계산하는지 테스트")
    fun calFinalPaymentTest(dDayEventDate: Int, totalDiscount: Int, totalPrice: Int) {
        val discountCalculator = DiscountCalculator(dDayEventDate, orderForms)
        discountCalculator.doDiscount(applicableEvents)
        discountCalculator.calFinalPayment(applicableEvents, totalDiscount)
        assertEquals(totalPrice, discountCalculator.finalPayment)
    }

    @Test
    @DisplayName("DiscountDetails 값이 정상적인지 확인")
    fun compareDiscountDetails(){
        val expected = listOf(
            Pair(EventType.D_DAY.promotionName, 1200),
            Pair(EventType.STAR_DAY.promotionName, 1000),
            Pair(EventType.WEEKDAYS.promotionName, 4046),
            Pair(EventType.FREE_GIFT.promotionName, 25000)
        )
        val discountCalculator = DiscountCalculator(2, orderForms)
        discountCalculator.doDiscount(applicableEvents)
        assertEquals(expected, discountCalculator.discountDetails)
    }
}