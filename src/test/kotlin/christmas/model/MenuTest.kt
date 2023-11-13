package christmas.model

import camp.nextstep.edu.missionutils.test.Assertions
import org.junit.jupiter.api.Assertions.*
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

    @Test
    @DisplayName("음료로만 구성되어있지 않을 경우 true, 음료로만 구성되어있을 경우 false를 반한하는지")
    fun areItemsNotOnlyBeverageTest() {
        assertFalse { menu.areItemsNotOnlyBeverage(listOf("제로콜라", "레드와인", "샴페인")) }
        assertTrue { menu.areItemsNotOnlyBeverage(listOf("제로콜라", "타파스", "초코케이크")) }
        assertTrue { menu.areItemsNotOnlyBeverage(listOf("티본스테이크", "타파스", "초코케이크")) }
    }
    
}