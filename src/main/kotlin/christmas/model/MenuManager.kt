package christmas.model

class MenuManager(private val inputMenu:List<String>) {
    val orderedMenu: List<String>
        get() = _orderedMenu
    private var _orderedMenu = listOf<String>()

    init {
        setOrderedMenu()
    }

    private fun setOrderedMenu() {
        if (inputMenu.isNotEmpty()) _orderedMenu = inputMenu
    }
}