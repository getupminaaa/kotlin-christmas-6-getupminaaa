package christmas.model

class PromotionManager(private val validDate:Int) {
    
    //d-day 계산해서 넘김 (이벤트 매니저한테)
    private fun calDDayEventDate(): Int {
        return validDate - 1
    }

    // 평일, 주말, 특별인지
}