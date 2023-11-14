package christmas.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

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

    @Test
    @DisplayName("금액에 따른 eventBadge값이 올바른지 확인")
    fun getEventBadgeTest() {
        val totalDiscount = listOf(5000,9000,10000,19000,20000,30000)
        val expected = listOf("별","별","트리","트리","산타","산타")
        val actualValues = mutableListOf<String>()
        totalDiscount.forEach {
            event.getEventBadgeType(it)
            actualValues.add(event.eventBadge)
        }
        assertEquals(expected,actualValues)
    }
}