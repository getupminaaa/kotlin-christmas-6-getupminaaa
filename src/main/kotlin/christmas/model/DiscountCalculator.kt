package christmas.model

class DiscountCalculator(
    private val applicableEvents: List<String>,
    private val categoryQuantities: List<Map<String, Int>>
) {
    private var dDayEventAmount = 0

    // -- 할인 --
    //크리스마스 디데이 할인 계산
    fun discountDDay(dDayEventDate: Int) {
        dDayEventAmount = 1000 + dDayEventDate * 100
    }

    //  평일할인 계산
    //  특별할인 계산
    //  주말할인 계산
    //  증정이벤트 계산

    //총 혜택 금액 계산

    //
}