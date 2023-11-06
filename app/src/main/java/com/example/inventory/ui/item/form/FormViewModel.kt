package com.example.inventory.ui.item.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.ui.model.ItemFormModel

abstract class FormViewModel(
): ViewModel() {

    var uiState: ItemFormUiState by mutableStateOf(ItemFormUiState())
        protected set


    fun onNameChange(newName: String) =
        updateUiState(uiState.itemFormModel.copy(name = newName))

    fun onPriceChange(newPrice: String) =
        updateUiState(uiState.itemFormModel.copy(price = newPrice))

    fun onQuantityChange(newQuantity: String) =
        updateUiState(uiState.itemFormModel.copy(quantity = newQuantity))

    private fun updateUiState(itemFormModel: ItemFormModel) {
        uiState =
            ItemFormUiState(
                itemFormModel = itemFormModel,
                isEntryValid = itemFormModel.isValid()
            )
    }
}