package christmas.model

class MenuManager(private val validOrder:List<Map<String,Int>>) {
    val orderedMenu: List<Map<String, Int>>
        get() = _orderedMenu
    private var _orderedMenu = listOf<Map<String,Int>>()

    init {
        setOrderedMenu()
    }

    private fun setOrderedMenu() {
        if (validOrder.isNotEmpty()) _orderedMenu = validOrder
    }
}