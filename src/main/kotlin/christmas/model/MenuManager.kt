package christmas.model

class MenuManager(private val validOrder: List<Map<String, Int>>) {

    val totalPrice: Int
        get() = _totalPrice
    private var _totalPrice = 0

    val orderMenuNameList:List<String>
        get() = _orderMenuNameList
    private var _orderMenuNameList = listOf<String>()

    val orderMenuQuantityList:List<Int>
        get() = _orderMenuQuantityList
    private var _orderMenuQuantityList = listOf<Int>()

    init {
        setOrderMenuNameList()
        setOrderMenuQuantityList()
    }

    private fun setOrderMenuNameList(){
        _orderMenuNameList = validOrder.flatMap { it.keys }
    }

    private fun setOrderMenuQuantityList(){
        _orderMenuQuantityList = validOrder.flatMap { it.values }
    }

    fun calculateTotalPrice(priceList: List<Int>) {
        var total = 0
        _orderMenuQuantityList.forEachIndexed { index, it ->
            total += priceList[index] * it
        }
        _totalPrice = total
    }

}