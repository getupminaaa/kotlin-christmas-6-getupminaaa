package christmas.model

import christmas.util.EventType

class PromotionManager(private val validDate:Int,private val totalPrice: Int) {

    private val weekDays = mapOf(EventType.WEEKDAYS to listOf(4, 5, 6, 7, 11, 12, 13, 14, 18, 19, 20, 21, 25, 26, 27, 28))
    private val starDays = mapOf(EventType.STAR_DAY to listOf(3, 10, 17, 24, 25, 31))

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

    private fun calDateEvent() {
        when {
            validDate == 25 -> _applicableEvents.addAll(listOf(EventType.WEEKDAYS.name,EventType.STAR_DAY.name))
            weekDays.flatMap { it.value }.contains(validDate) -> _applicableEvents.add(EventType.WEEKDAYS.name)
            starDays.flatMap { it.value }.contains(validDate) -> _applicableEvents.add(EventType.STAR_DAY.name)
            else -> _applicableEvents.add(EventType.WEEKEND.name)
        }
    }

    private fun isGiftEventApplicable() {
        if (totalPrice >= 120000) _applicableEvents.add(EventType.FREE_GIFT.name)

    }

    private fun checkApplicableEvent() {
        if (areAllEventsApplicable()) {
            isDDayEventApplicable()
            calDateEvent()
            isGiftEventApplicable()
        }
    }
}