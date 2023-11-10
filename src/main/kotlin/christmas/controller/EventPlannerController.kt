package christmas.controller

import christmas.model.DateManager
import christmas.model.MenuManager
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController {
    private val outputView = OutputView()
    private val inputView = InputView()

    fun run(){
        val dateManager = DateManager(inputView.inputDate)
        val menuManager = MenuManager(inputView.inputMenu)
        println(menuManager.orderedMenu)
        outputView.printEventMsg(dateManager.visitDate)
    }
}