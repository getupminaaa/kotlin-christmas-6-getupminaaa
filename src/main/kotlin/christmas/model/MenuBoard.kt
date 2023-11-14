package christmas.model

import christmas.model.data.MenuItem
import christmas.util.enums.MenuCategory

class MenuBoard {

    fun isItemInMenu(itemName: String) =
        MenuCategory.entries.flatMap { it.menuItems }.map { menuItem -> menuItem.name }.contains(itemName)

    fun areItemsNotOnlyBeverage(itemNames: List<String>): Boolean {
        val beverageMenu = MenuCategory.BEVERAGE.menuItems.map { menuItem -> menuItem.name }
        return itemNames.subtract(beverageMenu.toSet()).isNotEmpty()
    }

    fun getMenuItems(validOrder: List<Map<String, Int>>): List<MenuItem> {
        val menuItem = mutableListOf<MenuItem>()
        validOrder.flatMap { it.keys }.forEach { name ->
            menuItem.add(MenuCategory.entries.flatMap { it.menuItems }.first { it.name == name })
        }
        return menuItem
    }
}