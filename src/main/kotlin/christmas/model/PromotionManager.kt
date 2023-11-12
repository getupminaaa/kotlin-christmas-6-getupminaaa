package christmas.model

class PromotionManager(private val validDate:Int,private val totalPrice: Int) {

    private fun areAllEventsApplicable(): Boolean = (totalPrice >= 10000)

    
}