package christmas.validator

import christmas.util.Validator.isDateInRange
import christmas.util.Validator.isMenuDuplicate
import christmas.util.Validator.isMenuMatchRegex
import christmas.util.Validator.isMenuQuantityInRange
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = ["", "abcd", "abcd1234", "0", "32"])
    @DisplayName("날짜 입력이 유효하지 않다면 예외가 발생한다.")
    fun isDateInRangeTest(date: String) {
        assertThrows<IllegalArgumentException> {
            isDateInRange(date)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "abcd", "타파스", "0", "32"])
    @DisplayName("메뉴 입력이 유효하지 않다면 예외가 발생한다.")
    fun isMenuMatchRegexTest(menu: String) {
        assertThrows<IllegalArgumentException> {
            isMenuMatchRegex(menu)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스-1,해산물파스타-20", "초코케이크-21", "타파스-25,해산물파스타-40,초코케이크-30"])
    @DisplayName("메뉴 입력이 유효하지 않다면 예외가 발생한다.")
    fun isMenuQuantityInRangeTest(menus: String) {
        val menu = menus.split(',')
        assertThrows<IllegalArgumentException> {
            isMenuQuantityInRange(menu)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스-1,초코케이크-10,레드와인-3,타파스-1", "티본스테이크-1,티본스테이크-10,레드와인-3,타파스-1"])
    @DisplayName("메뉴 입력이 유효하지 않다면 예외가 발생한다.")
    fun isMenuDuplicateTest(menus: String) {
        val menu = menus.split(',')
        assertThrows<IllegalArgumentException> {
            isMenuDuplicate(menu)
        }
    }
}