package com.example.inventory.ui.model

import com.example.inventory.domain.Item
import com.example.inventory.ui.common.formatCurrency

data class ItemDetailsModel(
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

fun Item.toItemDetailsModel() = ItemDetailsModel(this)


