package com.example.inventory.ui.model

import com.example.inventory.domain.Item
import java.text.NumberFormat

data class ItemModel(
    val id: Int,
    val name: String,
    val price: String,
    val quantity: Int
){
    constructor(item: Item): this(
        id = item.id,
        name = item.name,
        price = formatCurrency(item.price),
        quantity = item.quantity
    )

    constructor(): this(Item())
}

private fun formatCurrency(value: Double): String =
    NumberFormat.getCurrencyInstance().format(value)

fun Item.toItemModel() = ItemModel(this)


