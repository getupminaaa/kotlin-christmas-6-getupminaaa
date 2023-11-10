package christmas.controller

import christmas.model.DateManager
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run(){
        val temp = 3
        outputView.printStartMsg()
        val dateManager = DateManager(inputView.inputDate)
        dateManager.getValidDate()
        inputView.getOrderedMenu()
        outputView.printEventMsg(temp)
    }
}