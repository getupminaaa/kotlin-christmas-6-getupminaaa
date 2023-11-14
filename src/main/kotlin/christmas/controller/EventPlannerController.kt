package christmas.controller

import christmas.model.DiscountCalculator
import christmas.model.Event
import christmas.model.Menu
import christmas.model.Order
import christmas.model.data.OrderForm
import christmas.util.validator.Validator.isOrderContainsFood
import christmas.util.validator.Validator.isOrderInMenu
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController {
    private lateinit var order: Order
    private lateinit var event: Event
    private lateinit var discountCalculator: DiscountCalculator

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
        printEventPreviewAndMenu()
        handleOrder()
        handleEvent(order.totalPrice)
        calculateDiscountAndPrintDetails(order.orderForms)
        calculateFinalPaymentAndPrint()
        calculateEventBadgeTypeAndPrint()
    }

    private fun printEventPreviewAndMenu() {
        outputView.printEventPreviewMsg(validDate)
        outputView.printMenu(validOrder)
    }

    private fun handleOrder() {
        order = Order(menu.getMenuItems(validOrder), validOrder)
        outputView.printTotalPrice(order.totalPrice)
    }

    private fun handleEvent(totalPrice: Int) {
        event = Event(validDate, totalPrice)
        outputView.printFreeGift(event.applicableEvents)
    }

    private fun calculateDiscountAndPrintDetails(orderForms: List<OrderForm>) {
        discountCalculator = DiscountCalculator(event.dDayEventDate, orderForms)
        discountCalculator.doDiscount(event.applicableEvents)
        outputView.printPromotionHistory(discountCalculator.discountDetails)
        outputView.printTotalDiscount(discountCalculator.totalDiscount)
    }

    private fun calculateFinalPaymentAndPrint() {
        discountCalculator.calFinalPayment(event.applicableEvents, order.totalPrice)
        outputView.printFinalPayment(discountCalculator.finalPayment)
    }

    private fun calculateEventBadgeTypeAndPrint() {
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
            checkOrderFromMenu(userInput)
            userInput
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidOrder()
        }
    }

    private fun checkOrderFromMenu(userInput: List<Map<String, Int>>) {
        checkMenu(userInput)
        checkNotOnlyBeverage(userInput)
    }

    private fun checkMenu(userInput: List<Map<String, Int>>) =
        userInput.flatMap { it.keys }.forEach {
            isOrderInMenu(menu.isItemInMenu(it))
        }

    private fun checkNotOnlyBeverage(userInput: List<Map<String, Int>>) {
        val temp = userInput.flatMap { it.keys }
        isOrderContainsFood(menu.areItemsNotOnlyBeverage(temp))
    }
}
