package christmas.controller

import christmas.model.DiscountCalculator
import christmas.model.MenuBoard
import christmas.model.Order
import christmas.model.Event
import christmas.util.Validator.isOrderContainsFood
import christmas.util.Validator.isOrderInMenu
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController {
    private val outputView = OutputView()
    private val inputView = InputView()
    private val menuBoard = MenuBoard()

    private var validDate = 0
    private var validOrder = listOf<Map<String, Int>>()

    init {
        validDate = getValidDate()
        validOrder = getValidOrder()
    }

    fun run() {
        outputView.printEventMsg(validDate)

        val order = Order()


        outputView.printMenu(validOrder)

        val itemPrices = menuBoard.getMenuItemPrices(validOrder)

        order.writeOrderForm(menuBoard.getMenuItems(validOrder), validOrder)
        order.calculateTotalPrice(validOrder, itemPrices)

        outputView.printTotalPrice(order.totalPrice)

        val event = Event(validDate, order.totalPrice)
        outputView.printFreeGift(event.applicableEvents)

//        orderManager.countMenuByCategory(menuBoard.getTypesOfMenuItems(orderManager.orderMenuNames))
//        val discountCalculator = DiscountCalculator(event.dDayEventDate, order.categoryQuantities)
//        discountCalculator.doDiscount(event.applicableEvents)
//        outputView.printPromotionHistory(discountCalculator.discountDetails)
//        outputView.printTotalDiscount(discountCalculator.totalDiscount)
//
//        discountCalculator.calFinalPayment(event.applicableEvents, order.totalPrice)
//        outputView.printFinalPayment(discountCalculator.finalPayment)
//
//        event.getEventBadgeType(discountCalculator.totalDiscount)
//        outputView.printEventBadge(event.eventBadge)
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
            menuCheck(userInput)
            onlyBeverage(userInput)
            userInput
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidOrder()
        }
    }

    private fun menuCheck(userInput: List<Map<String, Int>>) {
        userInput.flatMap { it.keys }.forEach { isOrderInMenu(menuBoard.isItemInMenu(it)) }
    }

    private fun onlyBeverage(userInput: List<Map<String, Int>>) {
        val temp = userInput.flatMap { it.keys }
        isOrderContainsFood(menuBoard.areItemsNotOnlyBeverage(temp))
    }


}
