package christmas.model

class Menu {
    enum class MenuCategory {
        애피타이저,
        메인,
        디저트,
        음료
    }
    private val menuListMap = mapOf(
        MenuCategory.애피타이저 to listOf(
            MenuItem("양송이수프", 6000),
            MenuItem("타파스", 5500),
            MenuItem("시저샐러드", 8000)
        ),
        MenuCategory.메인 to listOf(
            MenuItem("티본스테이크", 55000),
            MenuItem("바비큐립", 54000),
            MenuItem("해산물파스타", 35000),
            MenuItem("크리스마스파스타", 25000)
        ),
        MenuCategory.디저트 to listOf(
            MenuItem("초코케이크", 15000),
            MenuItem("아이스크림", 5000)
        ),
        MenuCategory.음료 to listOf(
            MenuItem("제로콜라", 3000),
            MenuItem("레드와인", 60000),
            MenuItem("샴페인", 25000)
        )
    )
    fun isItemInMenu(itemName: String): Boolean {
        return menuListMap.values.flatten().any { it.name.equals(itemName) }
    }
    fun areItemsNotOnlyBeverage(itemNames:List<String>):Boolean{
        val beverageMenu =  menuListMap[MenuCategory.음료]!!.map { it.name }
        return itemNames.subtract(beverageMenu.toSet()).isNotEmpty() //음료만으로 구성되어있지 않음 => true
    }
}