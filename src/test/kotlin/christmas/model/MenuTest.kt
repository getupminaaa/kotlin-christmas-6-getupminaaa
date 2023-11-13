package christmas.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MenuTest {
    private val menu = Menu()

    @Test
    @DisplayName("메뉴판에 있을 경우 true, 없을 경우 false를 반환하는지")
    fun isInItemMenuTest() {
        val input = listOf("타파스", "소갈비", "티본스테이크", "갈비찜", "계란말이", "제로콜라", "초코케이크")
        val expectedValue = listOf(true, false, true, false, false, true, true)
        val actualValue = input.map { menu.isItemInMenu(it) }
        assertEquals(expectedValue, actualValue)
    }
}