package christmas.model


class DateManager(private val validDate: Int) {
    val visitDate: Int
        get() = _visitDate
    private var _visitDate = 0

    init {
        setVisitDate()
    }

    private fun setVisitDate() {
        _visitDate = validDate
    }
}