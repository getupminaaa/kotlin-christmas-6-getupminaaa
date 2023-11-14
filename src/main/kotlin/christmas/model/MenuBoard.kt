package christmas.model

import christmas.model.data.MenuItem
import christmas.util.enums.MenuCategory

class MenuBoard {
    private val menuBoard = listOf(
        MenuItem(MenuCategory.APPETIZER.name, "양송이수프", 6000),
        MenuItem(MenuCategory.APPETIZER.name, "타파스", 5500),
        MenuItem(MenuCategory.APPETIZER.name, "시저샐러드", 8000),
        MenuItem(MenuCategory.MAIN_DISH.name, "티본스테이크", 55000),
        MenuItem(MenuCategory.MAIN_DISH.name, "바비큐립", 54000),
        MenuItem(MenuCategory.MAIN_DISH.name, "해산물파스타", 35000),
        MenuItem(MenuCategory.MAIN_DISH.name, "크리스마스파스타", 25000),
        MenuItem(MenuCategory.DESSERT.name, "초코케이크", 15000),
        MenuItem(MenuCategory.DESSERT.name, "아이스크림", 5000),
        MenuItem(MenuCategory.BEVERAGE.name, "제로콜라", 3000),
        MenuItem(MenuCategory.BEVERAGE.name, "레드와인", 60000),
        MenuItem(MenuCategory.BEVERAGE.name, "샴페인", 25000)
    )

    fun isItemInMenu(itemName: String): Boolean {
        return menuBoard.map { it.name }.contains(itemName)
    }

    fun areItemsNotOnlyBeverage(itemNames: List<String>): Boolean {
        val beverageMenu =
            menuBoard.filter { it.category == MenuCategory.BEVERAGE.name }.map { menuItem -> menuItem.name }
        return itemNames.subtract(beverageMenu.toSet()).isNotEmpty()
    }

    fun getMenuItems(validOrder: List<Map<String, Int>>): List<MenuItem> {
        val menuItem = mutableListOf<MenuItem>()
        validOrder.flatMap { it.keys }.forEach { name ->
            menuItem.add(menuBoard.first { it.name == name })
        }
        return menuItem
    }
}