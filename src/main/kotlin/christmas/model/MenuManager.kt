package christmas.model

class MenuManager(private val validOrder: List<Map<String, Int>>) {

    val totalPrice: Int
        get() = _totalPrice
    private var _totalPrice = 0

    val orderedMenuNameList:List<String>
        get() = _orderedMenuNameList
    private var _orderedMenuNameList = listOf<String>()

    val orderedMenuQuantityList:List<Int>
        get() = _orderedMenuQuantityList
    private var _orderedMenuQuantityList = listOf<Int>()

    init {
        setOrderedMenuNameList()
        setOrderMenuQuantityList()
    }

    private fun setOrderedMenuNameList(){
        _orderedMenuNameList = validOrder.flatMap { it.keys }
    }

    private fun setOrderMenuQuantityList(){
        _orderedMenuQuantityList = validOrder.flatMap { it.values }
    }

    fun calculateTotalPrice(priceList: List<Int>) {
        var total = 0
        _orderedMenuQuantityList.forEachIndexed { index, it ->
            total += priceList[index] * it
        }
        _totalPrice = total
    }

}