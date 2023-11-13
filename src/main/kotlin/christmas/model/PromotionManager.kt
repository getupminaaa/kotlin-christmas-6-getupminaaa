package christmas.model

import christmas.util.EventType

class PromotionManager(private val validDate: Int, private val totalPrice: Int) {

    private val weekends = listOf(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)
    private val starDays = listOf(3, 10, 17, 24, 25, 31)

    val applicableEvents: MutableList<String>
        get() = _applicableEvents
    private var _applicableEvents = mutableListOf<String>()
    val dDayEventDate: Int
        get() = _dDayEventDate
    private var _dDayEventDate = 0

    init {
        checkApplicableEvent()
    }

    private fun areAllEventsApplicable(): Boolean = (totalPrice >= 10000)

    private fun isDDayEventApplicable() {
        if (validDate <= 25) {
            _applicableEvents.add(EventType.D_DAY.name)
            calDDayEventDate()
        }
    }

    private fun calDDayEventDate() {
        _dDayEventDate = validDate - 1
    }

    private fun isWeekend() {
        if (weekends.contains(validDate)) _applicableEvents.add(EventType.WEEKEND.name)
    }

    private fun isWeekDays() {
        if (!weekends.contains(validDate)) _applicableEvents.add(EventType.WEEKDAYS.name)
    }

    private fun isStarDays() {
        if (starDays.contains(validDate)) _applicableEvents.add(EventType.STAR_DAY.name)
    }

    private fun calDateEvent() {
        isWeekend()
        isStarDays()
        isWeekDays()
    }

    private fun isGiftEventApplicable() {
        if (totalPrice >= 120000) _applicableEvents.add(EventType.FREE_GIFT.name)
    }

    private fun getBadgeName(index: Int): String {
        return EventType.BADGE_EVENT.promotionName.split(',')[index]
    }

    fun getEventBadgeType(totalDiscount: Int) {
        if (totalDiscount >= 5000) {
            when (totalDiscount) {
                in 5000 until 10000 -> _applicableEvents.add(getBadgeName(0))
                in 10000 until 20000 -> _applicableEvents.add(getBadgeName(1))
                else -> _applicableEvents.add(getBadgeName(2))
            }
        }
    }

    private fun checkApplicableEvent() {
        if (areAllEventsApplicable()) {
            isDDayEventApplicable()
            calDateEvent()
            isGiftEventApplicable()
        }
    }
}