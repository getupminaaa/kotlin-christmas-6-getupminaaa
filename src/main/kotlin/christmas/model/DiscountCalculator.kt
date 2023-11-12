package christmas.model

import christmas.util.EventType
import christmas.util.MenuCategory

class DiscountCalculator(
    private val dDayEventDate: Int,
    private val categoryQuantities: List<Map<String, Int>>
) {
    val totalDiscount:Int
        get() = _totalDiscount
    private var _totalDiscount = 0

    val discountDetails:List<Pair<String,Int>>
        get() = _discountsDetails
    private val _discountsDetails = mutableListOf<Pair<String, Int>>()

    // -- 할인 --
    //크리스마스 디데이 할인 계산
    private fun discountDDay() = 1000 + dDayEventDate * 100

    //  평일할인 계산 (디저트 메뉴를 메뉴 1개당 2,023원 할인)
    private fun discountWeekDays(): Int {
        var count = 0
        categoryQuantities.forEach { count += it.getOrDefault(MenuCategory.DESSERT.name, 0) }
        return count * 2023
    }

    //  주말할인 계산
    private fun discountWeekend(): Int {
        var count = 0
        categoryQuantities.forEach { count += it.getOrDefault(MenuCategory.MAIN_DISH.name, 0) }
        return count * 2023
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