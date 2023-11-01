package com.example.inventory.ui.item.details

import com.example.inventory.domain.Item
import com.example.inventory.ui.model.ItemDetailsModel
import com.example.inventory.ui.model.toItemDetailsModel


/**
 * UI state for ItemDetailsScreen
 */
data class ItemDetailsUiState(
    val outOfStock: Boolean,
    val item: ItemDetailsModel
){
    constructor(item: Item): this (
        outOfStock = item.quantity <= 0,
        item = item.toItemDetailsModel()
    )

    constructor(): this(Item())
}