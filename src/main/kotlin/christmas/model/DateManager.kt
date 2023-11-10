package christmas.model

import christmas.util.Validator.isItInteger

class DateManager(private val inputDate:String){
    fun getValidDate(){
        isItInteger(inputDate)
    }
}