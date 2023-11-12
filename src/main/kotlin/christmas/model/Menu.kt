package christmas.model

class Menu {
    enum class MenuCategory {
        APPETIZER,
        MAIN_DISH,
        DESSERT,
        BEVERAGE
    }

    private val menuBoard = mapOf(
        MenuCategory.APPETIZER to listOf(
            MenuItem("양송이수프", 6000),
            MenuItem("타파스", 5500),
            MenuItem("시저샐러드", 8000)
        ),
        MenuCategory.MAIN_DISH to listOf(
            MenuItem("티본스테이크", 55000),
            MenuItem("바비큐립", 54000),
            MenuItem("해산물파스타", 35000),
            MenuItem("크리스마스파스타", 25000)
        ),
        MenuCategory.DESSERT to listOf(
            MenuItem("초코케이크", 15000),
            MenuItem("아이스크림", 5000)
        ),
        MenuCategory.BEVERAGE to listOf(
            MenuItem("제로콜라", 3000),
            MenuItem("레드와인", 60000),
            MenuItem("샴페인", 25000)
        )
    )

    val typesOfMenuItems: List<String>
        get() = _typesOfMenuItems

    private var _typesOfMenuItems = mutableListOf<String>()

    fun isItemInMenu(itemName: String): Boolean {
        return menuBoard.values.flatten().any { it.name == itemName }
    }

    fun areItemsNotOnlyBeverage(itemNames: List<String>): Boolean {
        val beverageMenu = menuBoard[MenuCategory.BEVERAGE]!!.map { it.name }
        return itemNames.subtract(beverageMenu.toSet()).isNotEmpty() //음료만으로 구성되어있지 않음 => true
    }

    fun getMenuItemPrice(itemNames: List<String>): List<Int> {
        val prices = mutableListOf<Int>()
        itemNames.forEachIndexed { index, _ ->
            prices.add(menuBoard.values.flatten().find { it.name == itemNames[index] }!!.price)
        }
        return prices
    }

    fun checkTypeOfFoods(itemNames: List<String>) {
        val appetizerNames = menuBoard[MenuCategory.APPETIZER]!!.map { it.name }
        val mainDishesNames = menuBoard[MenuCategory.MAIN_DISH]!!.map { it.name }
        val dessertNames = menuBoard[MenuCategory.DESSERT]!!.map { it.name }
        val beverageNames = menuBoard[MenuCategory.BEVERAGE]!!.map { it.name }

        itemNames.forEach {
            when {
                appetizerNames.contains(it) -> _typesOfMenuItems.add(MenuCategory.APPETIZER.name)
                mainDishesNames.contains(it) -> _typesOfMenuItems.add(MenuCategory.MAIN_DISH.name)
                dessertNames.contains(it) -> _typesOfMenuItems.add(MenuCategory.DESSERT.name)
                beverageNames.contains(it) -> _typesOfMenuItems.add(MenuCategory.BEVERAGE.name)
            }
        }
    }
}