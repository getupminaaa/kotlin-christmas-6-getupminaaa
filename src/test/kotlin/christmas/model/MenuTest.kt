package christmas.model

import christmas.model.data.MenuItem
import christmas.util.enums.MenuBoard
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MenuTest {
    private val menu = Menu()
    private val validOrder = listOf(
        mapOf("타파스" to 1),
        mapOf("초코케이크" to 1),
        mapOf("레드와인" to 1),
        mapOf("해산물파스타" to 1),
        mapOf("바비큐립" to 1),
        mapOf("시저샐러드" to 1)
    )

    @ParameterizedTest
    @CsvSource(
        value = ["타파스,true", "소갈비,false", "티본스테이크,true", "갈비찜,false", "계란말이,false", "제로콜라,true", "초코케이크,true"],
        delimiter = ','
    )
    fun isInItemMenuTest(inputMenu: String, expectedValue: Boolean) {
        val actualValue = menu.isItemInMenu(inputMenu)
        assertEquals(expectedValue, actualValue)
    }

    @Test
    @DisplayName("음료로만 구성되어있지 않을 경우 true, 음료로만 구성되어있을 경우 false를 반한하는지")
    fun areItemsNotOnlyBeverageTest() {
        assertFalse { menu.areItemsNotOnlyBeverage(listOf("제로콜라", "레드와인", "샴페인")) }
        assertTrue { menu.areItemsNotOnlyBeverage(listOf("제로콜라", "타파스", "초코케이크")) }
        assertTrue { menu.areItemsNotOnlyBeverage(listOf("티본스테이크", "타파스", "초코케이크")) }
    }

    @Test
    @DisplayName("입력된 여러 메뉴들에 대해 메뉴아이템을 잘 반환하는지")
    fun getTypesOfMenuItemsTest() {
        val expected = listOf(
            MenuItem(MenuBoard.APPETIZER.name, "타파스", 5500),
            MenuItem(MenuBoard.DESSERT.name, "초코케이크", 15000),
            MenuItem(MenuBoard.BEVERAGE.name, "레드와인", 60000),
            MenuItem(MenuBoard.MAIN_DISH.name, "해산물파스타", 35000),
            MenuItem(MenuBoard.MAIN_DISH.name, "바비큐립", 54000),
            MenuItem(MenuBoard.APPETIZER.name, "시저샐러드", 8000)
        )
        val actualValue = menu.getMenuItems(validOrder)
        assertEquals(expected, actualValue)
    }
}