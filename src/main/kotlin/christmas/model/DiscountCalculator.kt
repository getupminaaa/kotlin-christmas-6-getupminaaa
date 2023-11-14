package christmas.model

import christmas.model.data.OrderForm
import christmas.util.EventType
import christmas.util.MenuCategory

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
    private fun discountDDay() = 1000 + dDayEventDate * 100

    private fun discountWeekDays() =
        orderForms.find { it.menuItem.category == MenuCategory.DESSERT.name }?.quantity?.times(2023) ?: 0

    private fun discountWeekend() =
        orderForms.find { it.menuItem.category == MenuCategory.MAIN_DISH.name }?.quantity?.times(2023) ?: 0


    fun calFinalPayment(applicableEvents: List<String>, totalPrice: Int){
        _finalPayment = totalPrice - totalDiscount
        if (applicableEvents.contains(EventType.FREE_GIFT.name)) _finalPayment += 25000 else _finalPayment
    }

    fun doDiscount(applicableEvents: List<String>) {
        applicableEvents.forEach {
            when {
                it.contains(EventType.D_DAY.name) -> {
                    val amount = discountDDay()
                    _totalDiscount += amount
                    _discountsDetails.add(EventType.D_DAY.promotionName to amount)
                }

                it.contains(EventType.WEEKDAYS.name) -> {
                    val amount = discountWeekDays()
                    _totalDiscount += amount
                    _discountsDetails.add(EventType.WEEKDAYS.promotionName to amount)
                }

                it.contains(EventType.WEEKEND.name) -> {
                    val amount = discountWeekend()
                    _totalDiscount += amount
                    _discountsDetails.add(EventType.WEEKEND.promotionName to amount)
                }

                it.contains(EventType.STAR_DAY.name) -> {
                    _totalDiscount += 1000
                    _discountsDetails.add(EventType.STAR_DAY.promotionName to 1000)
                }

                it.contains(EventType.FREE_GIFT.name) -> {
                    _totalDiscount += 25000
                    _discountsDetails.add(EventType.FREE_GIFT.promotionName to 25000)
                }
            }
        }
    }
}