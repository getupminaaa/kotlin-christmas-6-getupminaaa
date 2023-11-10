package christmas.controller

import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run(){
        val temp = 3
        outputView.printStartMsg()
        inputView.getRestaurantVisitDate()
        inputView.getOrderedMenu()
        outputView.printEventMsg(temp)
    }
}