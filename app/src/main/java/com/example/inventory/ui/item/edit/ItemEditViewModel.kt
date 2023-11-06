/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.ui.item.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.repository.ItemsRepository
import com.example.inventory.ui.item.form.FormViewModel
import com.example.inventory.ui.model.ItemFormModel
import com.example.inventory.ui.item.form.toItemFormUiState
import com.example.inventory.ui.navigation.ItemEditDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel to retrieve and update an item from the [ItemsRepository]'s data source.
 */
@HiltViewModel
class ItemEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val itemsRepository: ItemsRepository
) : FormViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            uiState = itemsRepository.getItemByIdStream(itemId)
                .filterNotNull()
                .first()
                .toItemFormUiState(isEntryValid = true)
        }
    }

    /**
     * Update the item in the [ItemsRepository]'s data source
     */
    fun updateItem() = viewModelScope.launch {
        val formData: ItemFormModel = uiState.itemFormModel
        if (formData.isValid()) {
            itemsRepository.updateItem(formData.toItem())
        }
    }
}
