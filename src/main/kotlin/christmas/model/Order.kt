package christmas.model

import christmas.model.data.MenuItem
import christmas.model.data.OrderForm

class Order(private val menuItems: List<MenuItem>, private val validOrder: List<Map<String, Int>>) {

    private var _totalPrice = 0
    val totalPrice: Int
        get() = _totalPrice

    private var _orderForms = mutableListOf<OrderForm>()
    val orderForms: List<OrderForm>
        get() = _orderForms

    init {
        writeOrderForm()
        calculateTotalPrice()
    }

    private fun writeOrderForm() {
        val quantities = validOrder.flatMap { it.values }
        menuItems.forEachIndexed { index, menuItem ->
            _orderForms.add(OrderForm(menuItem, quantities[index]))
        }
    }

    private fun calculateTotalPrice() =
        orderForms.forEach { (menuItems, quantity) -> _totalPrice += menuItems.price * quantity }
}