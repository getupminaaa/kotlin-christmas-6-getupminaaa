package christmas.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class EventTest {
    private val totalPrice = 142000
    private val validDate = 3
    val event = Event(validDate, totalPrice)

    @Test
    @DisplayName("applicableEvent값이 올바른지 확인")
    fun compareApplicableEvent() {
        val expected = setOf("D_DAY", "STAR_DAY", "WEEKDAYS", "FREE_GIFT")
        assertThat(event.applicableEvents.subtract(expected).isEmpty()).isTrue()
    }

}