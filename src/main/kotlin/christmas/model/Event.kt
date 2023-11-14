package christmas.model

import christmas.util.enums.EventDay
import christmas.util.enums.EventType

class Event(private val validDate: Int, private val totalPrice: Int) {

    val applicableEvents: MutableList<String>
        get() = _applicableEvents
    private var _applicableEvents = mutableListOf<String>()

    val dDayEventDate: Int
        get() = _dDayEventDate
    private var _dDayEventDate = 0

    val eventBadge: String
        get() = _eventBadge
    private var _eventBadge = ""

    init {
        checkApplicableEvent()
    }

    private fun areAllEventsApplicable(): Boolean = (totalPrice >= EVENT_BASE_AMOUNT)

    private fun isDDayEventApplicable() {
        if (validDate <= XMAS_DAY) {
            _applicableEvents.add(EventType.D_DAY.name)
            calDDayEventDate()
        }
    }

    private fun calDDayEventDate() {
        _dDayEventDate = validDate - D_DAY_SUBTRACTION_FACTOR
    }

    private fun isWeekend() {
        if (EventDay.WEEKEND_DATES.dates.contains(validDate)) _applicableEvents.add(EventType.WEEKEND.name)
    }

    private fun isWeekDays() {
        if (!EventDay.WEEKEND_DATES.dates.contains(validDate)) _applicableEvents.add(EventType.WEEKDAYS.name)
    }

    private fun isStarDays() {
        if (EventDay.STAR_DAY_DATES.dates.contains(validDate)) _applicableEvents.add(EventType.STAR_DAY.name)
    }

    private fun calDateEvent() {
        isWeekend()
        isStarDays()
        isWeekDays()
    }

    private fun isGiftEventApplicable() {
        if (totalPrice >= FREE_GIFT_BASE_AMOUNT) _applicableEvents.add(EventType.FREE_GIFT.name)
    }

    private fun getBadgeName(index: Int): String {
        return EventType.BADGE_EVENT.promotionName.split(',')[index]
    }

    fun getEventBadgeType(totalDiscount: Int) {
        if (totalDiscount >= EVENT_BADGE_STAR) {
            _eventBadge = when (totalDiscount) {
                in EVENT_BADGE_STAR until EVENT_BADGE_TREE -> getBadgeName(0)
                in EVENT_BADGE_TREE until EVENT_BADGE_SANTA -> getBadgeName(1)
                else -> getBadgeName(2)
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

    companion object {
        private const val XMAS_DAY = 25
        private const val EVENT_BASE_AMOUNT = 10000
        private const val EVENT_BADGE_TREE = 10000
        private const val FREE_GIFT_BASE_AMOUNT = 120000
        private const val EVENT_BADGE_STAR = 5000
        private const val EVENT_BADGE_SANTA = 20000
        private const val D_DAY_SUBTRACTION_FACTOR = 1
    }
}