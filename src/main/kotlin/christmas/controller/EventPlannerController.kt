package christmas.controller

import christmas.model.DateManager
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController {
    private val outputView = OutputView()
    private val inputView = InputView()

    fun run(){
        val dateManager = DateManager(inputView.inputDate)
        outputView.printEventMsg(dateManager.visitDate)
    }
}