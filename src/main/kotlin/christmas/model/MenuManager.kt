package christmas.model

class MenuManager(private val validOrder: List<Map<String, Int>>) {

    val totalPrice: Int
        get() = _totalPrice
    private var _totalPrice = 0

    val orderMenuNames: List<String>
        get() = _orderMenuNames
    private var _orderMenuNames = listOf<String>()

    val categoryQuantities:List<Map<String,Int>>
        get() = _categoryQuantities

    private var  _categoryQuantities = mutableListOf(mapOf<String,Int>())

    private var _orderMenuQuantities = listOf<Int>()

    init {
        setOrderMenuNames()
        setOrderMenuQuantities()
    }

    private fun setOrderMenuNames() {
        _orderMenuNames = validOrder.flatMap { it.keys }
    }

    private fun setOrderMenuQuantities() {
        _orderMenuQuantities = validOrder.flatMap { it.values }
    }

    fun calculateTotalPrice(priceList: List<Int>) {
        var total = 0
        _orderMenuQuantities.forEachIndexed { index, it ->
            total += priceList[index] * it
        }
        _totalPrice = total
    }

    fun countMenuByCategory(category: List<String>){
        val result = mutableListOf(mapOf<String, Int>())
        _orderMenuQuantities.forEachIndexed { index, i ->
            result.add(mapOf(category[index] to i))
        }
        _categoryQuantities = result
    }

}