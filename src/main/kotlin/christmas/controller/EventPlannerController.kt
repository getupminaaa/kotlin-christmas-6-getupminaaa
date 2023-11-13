package christmas.controller

import christmas.model.DiscountCalculator
import christmas.model.Menu
import christmas.model.MenuManager
import christmas.model.PromotionManager
import christmas.util.Validator.isOrderContainsFood
import christmas.util.Validator.isOrderInMenu
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
        outputView.printEventMsg(validDate)

        val menuManager = MenuManager(validOrder)
        outputView.printMenu(validOrder)

        menuManager.calculateTotalPrice(menu.getMenuItemPrice(menuManager.orderMenuNames))
        outputView.printTotalPrice(menuManager.totalPrice)

        val promotionManager = PromotionManager(validDate, menuManager.totalPrice)
        outputView.printFreeGift(promotionManager.applicableEvents)

        menu.getTypesOfMenuItems(menuManager.orderMenuNames)
        menuManager.countMenuByCategory(menu.typesOfMenuItems)

        val discountCalculator = DiscountCalculator(promotionManager.dDayEventDate, menuManager.categoryQuantities)
        discountCalculator.doDiscount(promotionManager.applicableEvents)
        outputView.printPromotionHistory(discountCalculator.discountDetails)
        outputView.printTotalDiscount(discountCalculator.totalDiscount)
        discountCalculator.calFinalPayment(promotionManager.applicableEvents, menuManager.totalPrice)
        outputView.printFinalPayment(discountCalculator.finalPayment)
        promotionManager.getEventBadgeType(discountCalculator.totalDiscount)
        outputView.printEventBadge(promotionManager.eventBadge)
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
        userInput.flatMap { it.keys }.forEach { isOrderInMenu(menu.isItemInMenu(it)) }
    }

    private fun onlyBeverage(userInput: List<Map<String, Int>>) {
        val temp = userInput.flatMap { it.keys }
        isOrderContainsFood(menu.areItemsNotOnlyBeverage(temp))
    }


}
