package com.example.inventory.ui.item.form

import com.example.inventory.domain.Item
import com.example.inventory.ui.model.ItemFormModel
import com.example.inventory.ui.model.toItemFormData

/**
 * Represents Ui State for an Item.
 */
data class ItemFormUiState(
    val itemFormModel: ItemFormModel = ItemFormModel(),
    val isEntryValid: Boolean = false
)

fun Item.toItemFormUiState(isEntryValid: Boolean = false): ItemFormUiState =
    ItemFormUiState(
        itemFormModel = this.toItemFormData(),
        isEntryValid = isEntryValid
    )