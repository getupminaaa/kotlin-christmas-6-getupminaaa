package christmas.model

import christmas.model.data.MenuItem
import christmas.model.data.OrderForm
import christmas.util.enums.MenuBoard
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OrderTest {
    private val menuItems = listOf(
        MenuItem(MenuBoard.APPETIZER.name, "타파스", 5500),
        MenuItem(MenuBoard.DESSERT.name, "초코케이크", 15000),
        MenuItem(MenuBoard.BEVERAGE.name, "레드와인", 60000),
        MenuItem(MenuBoard.MAIN_DISH.name, "해산물파스타", 35000),
        MenuItem(MenuBoard.MAIN_DISH.name, "바비큐립", 54000),
        MenuItem(MenuBoard.APPETIZER.name, "시저샐러드", 8000)
    )
    private val validOrder = listOf(
        mapOf("타파스" to 1),
        mapOf("초코케이크" to 2),
        mapOf("레드와인" to 1),
        mapOf("해산물파스타" to 1),
        mapOf("바비큐립" to 3),
        mapOf("시저샐러드" to 1)
    )
    private val order = Order(menuItems, validOrder)

    @Test
    @DisplayName("order의 totalPrice와 비교를 통해 계산이 정상적인지 확인")
    fun compareTotalPrice() {
        val expected = 300500
        assertEquals(expected, order.totalPrice)
    }

    @Test
    @DisplayName("orderForms와 비교를 통해 정상적으로 값이 들어가는지 확인 ")
    fun compareOrderForms() {
        val expected = listOf(
            OrderForm(MenuItem(MenuBoard.APPETIZER.name, "타파스", 5500), 1),
            OrderForm(MenuItem(MenuBoard.DESSERT.name, "초코케이크", 15000), 2),
            OrderForm(MenuItem(MenuBoard.BEVERAGE.name, "레드와인", 60000), 1),
            OrderForm(MenuItem(MenuBoard.MAIN_DISH.name, "해산물파스타", 35000), 1),
            OrderForm(MenuItem(MenuBoard.MAIN_DISH.name, "바비큐립", 54000), 3),
            OrderForm(MenuItem(MenuBoard.APPETIZER.name, "시저샐러드", 8000), 1)
        )
        assertEquals(expected,order.orderForms)
    }
}