package christmas.model

import christmas.model.data.OrderForm
import christmas.util.enums.EventType
import christmas.util.enums.MenuCategory

class DiscountCalculator(
    private val dDayEventDate: Int,
    private val orderForms: List<OrderForm>
) {
    val totalDiscount: Int
        get() = _totalDiscount
    private var _totalDiscount = 0

    val discountDetails: List<Pair<String, Int>>
        get() = _discountsDetails
    private val _discountsDetails = mutableListOf<Pair<String, Int>>()

    val finalPayment: Int
        get() = _finalPayment
    private var _finalPayment = 0

    //크리스마스 디데이 할인 계산
    private fun discountDDay() = D_DAY_START_DISCOUNT + dDayEventDate * D_DAY_DISCOUNT_INCREMENT

    private fun discountWeekDays() =
        orderForms.find { it.menuItem.category == MenuCategory.DESSERT.name }?.quantity?.times(DATE_DISCOUNT) ?: 0

    private fun discountWeekend() =
        orderForms.find { it.menuItem.category == MenuCategory.MAIN_DISH.name }?.quantity?.times(DATE_DISCOUNT) ?: 0

    private fun getDiscountDetails(event: String) =
        when (event) {
            EventType.D_DAY.name -> EventType.D_DAY to discountDDay()
            EventType.WEEKDAYS.name -> EventType.WEEKDAYS to discountWeekDays()
            EventType.WEEKEND.name -> EventType.WEEKEND to discountWeekend()
            EventType.STAR_DAY.name -> EventType.STAR_DAY to STAR_DAY_DISCOUNT
            EventType.FREE_GIFT.name -> EventType.FREE_GIFT to FREE_GIFT_PRICE
            else -> null to 0
        }

    fun calFinalPayment(applicableEvents: List<String>, totalPrice: Int) {
        _finalPayment = totalPrice - _totalDiscount
        if (applicableEvents.contains(EventType.FREE_GIFT.name)) _finalPayment += FREE_GIFT_PRICE
    }

    fun doDiscount(applicableEvents: List<String>) {
        applicableEvents.forEach { event ->
            val (eventType, amount) = getDiscountDetails(event)
            if (eventType != null) _discountsDetails.add(eventType.promotionName to amount)
            _totalDiscount += amount
        }
    }

    companion object{
        private const val FREE_GIFT_PRICE = 25000
        private const val D_DAY_START_DISCOUNT = 1000
        private const val STAR_DAY_DISCOUNT = 1000
        private const val D_DAY_DISCOUNT_INCREMENT = 100
        private const val DATE_DISCOUNT = 2023
    }
}