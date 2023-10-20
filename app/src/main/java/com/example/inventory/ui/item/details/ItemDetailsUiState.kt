package com.example.inventory.ui.item.details

import com.example.inventory.domain.Item
import com.example.inventory.ui.model.ItemModel
import com.example.inventory.ui.model.toItemModel


/**
 * UI state for ItemDetailsScreen
 */
data class ItemDetailsUiState(
    val outOfStock: Boolean,
    val item: ItemModel
){
    constructor(item: Item): this (
        outOfStock = item.quantity <= 0,
        item = item.toItemModel()
    )

    constructor(): this(Item())
}