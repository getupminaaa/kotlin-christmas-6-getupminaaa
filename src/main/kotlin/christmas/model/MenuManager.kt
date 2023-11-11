package christmas.model

class MenuManager(private val validOrder: List<Map<String, Int>>) {

    val totalPrice: Int
        get() = _totalPrice
    private var _totalPrice = 0

    val orderedMenuNameList:List<String>
        get() = _orderedMenuNameList
    private var _orderedMenuNameList = listOf<String>()

    init {
        setOrderedMenuNameList()
    }



    private fun setOrderedMenuNameList(){
        _orderedMenuNameList = validOrder.flatMap { it.keys }
    }

    fun calculateTotalPrice(priceList: List<Int>) {
        var total = 0
        validOrder.flatMap {map -> map.values }.forEachIndexed { index, it ->
            total += priceList[index] * it
        }
        _totalPrice = total
    }

}