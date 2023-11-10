package christmas.model


class DateManager(private val inputDate: String) {
    val visitDate: Int
        get() = _visitDate
    private var _visitDate = 0

    init {
        setVisitDate()
    }

    private fun setVisitDate() {
        if (inputDate.isNotEmpty()) _visitDate = inputDate.toInt()
    }
}