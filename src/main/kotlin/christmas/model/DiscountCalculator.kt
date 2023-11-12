package christmas.model

import christmas.util.EventType
import christmas.util.MenuCategory

class DiscountCalculator(
    private val dDayEventDate: Int,
    private val categoryQuantities: List<Map<String, Int>>
) {
    private var _totalAmount = 0
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
                it.contains(EventType.D_DAY.name) -> _totalAmount += discountDDay()
                it.contains(EventType.WEEKDAYS.name) -> _totalAmount += discountWeekDays()
                it.contains(EventType.WEEKEND.name) -> _totalAmount += discountWeekend()
                it.contains(EventType.STAR_DAY.name) -> _totalAmount += 1000
                it.contains(EventType.FREE_GIFT.name) -> _totalAmount += 25000
            }
        }
        println("total = $_totalAmount")
    }
}