package christmas.util.enums

import christmas.model.data.MenuItem

enum class MenuBoard(val menuItems: List<MenuItem>) {
    APPETIZER(
        listOf(
            MenuItem("APPETIZER", "양송이수프", 6000),
            MenuItem("APPETIZER", "타파스", 5500),
            MenuItem("APPETIZER", "시저샐러드", 8000)
        )
    ),
    MAIN_DISH(
        listOf(
            MenuItem("MAIN_DISH", "티본스테이크", 55000),
            MenuItem("MAIN_DISH", "바비큐립", 54000),
            MenuItem("MAIN_DISH", "해산물파스타", 35000),
            MenuItem("MAIN_DISH", "크리스마스파스타", 25000)
        )
    ),
    DESSERT(
        listOf(
            MenuItem("DESSERT", "초코케이크", 15000),
            MenuItem("DESSERT", "아이스크림", 5000),
        )
    ),
    BEVERAGE(
        listOf(
            MenuItem("BEVERAGE", "제로콜라", 3000), MenuItem("BEVERAGE", "레드와인", 60000), MenuItem("BEVERAGE", "샴페인", 25000)
        )
    )
}