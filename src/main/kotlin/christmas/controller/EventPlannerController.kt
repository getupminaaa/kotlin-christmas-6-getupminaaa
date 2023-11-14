package christmas.controller

import christmas.model.DiscountCalculator
import christmas.model.Event
import christmas.model.Menu
import christmas.model.Order
import christmas.util.validator.Validator.isOrderContainsFood
import christmas.util.validator.Validator.isOrderInMenu
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController {
    private val outputView = OutputView()
    private val inputView = InputView()
    private val menu = Menu()

    private var validDate = 0
    private var validOrder = listOf<Map<String, Int>>()

    init {
        validDate = getValidDate()
        validOrder = getValidOrder()
    }

    fun run() {
        //방문 일자 출력
        outputView.printEventMsg(validDate)

        //주문 모델
        outputView.printMenu(validOrder)

        val order = Order(menu.getMenuItems(validOrder), validOrder)
        outputView.printTotalPrice(order.totalPrice)

        val event = Event(validDate, order.totalPrice)
        outputView.printFreeGift(event.applicableEvents)

        val discountCalculator = DiscountCalculator(event.dDayEventDate, order.orderForms)

        discountCalculator.doDiscount(event.applicableEvents)

        outputView.printPromotionHistory(discountCalculator.discountDetails)
        outputView.printTotalDiscount(discountCalculator.totalDiscount)

        discountCalculator.calFinalPayment(event.applicableEvents, order.totalPrice)
        outputView.printFinalPayment(discountCalculator.finalPayment)

        event.getEventBadgeType(discountCalculator.totalDiscount)
        outputView.printEventBadge(event.eventBadge)
    }

    private fun getValidDate(): Int {
        return try {
            val userInput = inputView.getVisitDate()
            userInput
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidDate()
        }
    }

    private fun getValidOrder(): List<Map<String, Int>> {
        return try {
            val userInput = inputView.getOrderMenu()
            checkMenu(userInput)
            checkNotOnlyBeverage(userInput)
            userInput
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidOrder()
        }
    }

    private fun checkMenu(userInput: List<Map<String, Int>>) {
        userInput.flatMap { it.keys }.forEach { isOrderInMenu(menu.isItemInMenu(it)) }
    }

    private fun checkNotOnlyBeverage(userInput: List<Map<String, Int>>) {
        val temp = userInput.flatMap { it.keys }
        isOrderContainsFood(menu.areItemsNotOnlyBeverage(temp))
    }
}
