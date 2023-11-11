package christmas.model

class MenuManager(private val validOrder: List<Map<String, Int>>) {
    val orderedMenu: List<Map<String, Int>>
        get() = _orderedMenu
    private var _orderedMenu = listOf<Map<String, Int>>()

    val totalPrice: Int
        get() = _totalPrice
    private var _totalPrice = 0

    val orderedMenuNameList:List<String>
        get() = _orderedMenuNameList
    private var _orderedMenuNameList = listOf<String>()

    init {
        setOrderedMenu()
        setOrderedMenuNameList()
    }

    private fun setOrderedMenu() {
        if (validOrder.isNotEmpty()) _orderedMenu = validOrder
    }

    private fun setOrderedMenuNameList(){
        _orderedMenuNameList = _orderedMenu.flatMap { it.keys }
    }

    fun calculateTotalPrice(priceList: List<Int>) {
        var total = 0
        orderedMenu.flatMap {map -> map.values }.forEachIndexed { index, it ->
            total += priceList[index] * it
        }
        _totalPrice = total
    }

}