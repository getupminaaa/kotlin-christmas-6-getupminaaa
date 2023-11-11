package christmas.controller

import christmas.model.DateManager
import christmas.model.MenuManager
import christmas.util.Validator
import christmas.util.Validator.isDateInRange
import christmas.view.InputView
import christmas.view.OutputView

class EventPlannerController {
    private val outputView = OutputView()
    private val inputView = InputView()
    private var validDate = 0
    init {
        validDate = getValidDate()
    }

    fun run(){
        val dateManager = DateManager(validDate)
        outputView.printEventMsg(dateManager.visitDate)

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

}