package christmas.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class EventTest {
    private val totalPrice = 142000
    private val validDate = 3
    private val event = Event(validDate, totalPrice)

    @Test
    @DisplayName("applicableEvent값이 올바른지 확인")
    fun compareApplicableEvent() {
        val expected = setOf("D_DAY", "STAR_DAY", "WEEKDAYS", "FREE_GIFT")
        assertThat(event.applicableEvents.subtract(expected).isEmpty()).isTrue()
    }

    @ParameterizedTest
    @CsvSource(value = ["5000,별", "9000,별", "10000,트리", "19000,트리", "20000,산타", "30000,산타"], delimiter = ',')
    @DisplayName("금액에 따른 eventBadge값이 올바른지 확인")
    fun getEventBadgeTest(totalDiscount: Int, expected: String) {
        event.getEventBadgeType(totalDiscount)
        assertEquals(expected, event.eventBadge)
    }

    @ParameterizedTest
    @CsvSource(value = ["3,2", "10,9", "21,20", "25,24"], delimiter = ',')
    @DisplayName("dDayEventDate 값이 올바른지 확인")
    fun compareDDayEventDate(validDate: Int, expected: Int) {
        val event = Event(validDate, 10000)
        assertEquals(expected, event.dDayEventDate)
    }

}