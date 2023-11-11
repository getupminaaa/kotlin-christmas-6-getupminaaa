package christmas.model

class MenuManager(private val validOrder: List<Map<String, Int>>) {

    val totalPrice: Int
        get() = _totalPrice
    private var _totalPrice = 0

    val orderMenuNames:List<String>
        get() = _orderMenuNames
    private var _orderMenuNames = listOf<String>()

    val orderMenuQuantities:List<Int>
        get() = _orderMenuQuantities
    private var _orderMenuQuantities = listOf<Int>()

    init {
        setOrderMenuNames()
        setOrderMenuQuantities()
    }

    private fun setOrderMenuNames(){
        _orderMenuNames = validOrder.flatMap { it.keys }
    }

    private fun setOrderMenuQuantities(){
        _orderMenuQuantities = validOrder.flatMap { it.values }
    }

    fun calculateTotalPrice(priceList: List<Int>) {
        var total = 0
        _orderMenuQuantities.forEachIndexed { index, it ->
            total += priceList[index] * it
        }
        _totalPrice = total
    }

}