package christmas.model

import christmas.util.Validator.isDateInRange

class DateManager(private val inputDate: String) {
    val visitDate: Int
        get() = _visitDate
    private var _visitDate = 0

    init {
        getValidVisitDate()
    }

    private fun getValidVisitDate() {
        isDateInRange(inputDate)
        _visitDate = inputDate.toInt()
    }
}