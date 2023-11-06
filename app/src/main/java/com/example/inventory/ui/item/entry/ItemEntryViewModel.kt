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

package com.example.inventory.ui.item.entry

import androidx.lifecycle.viewModelScope
import com.example.inventory.data.local.LocalItem
import com.example.inventory.data.repository.ItemsRepository
import com.example.inventory.ui.item.form.FormViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel to validate and insert items in the Room database.
 */
@HiltViewModel
class ItemEntryViewModel @Inject constructor(
    private val itemsRepository: ItemsRepository
) : FormViewModel() {

    /**
     * Inserts an [LocalItem] in the Room database
     */
    fun saveItem() = viewModelScope.launch{
        if (uiState.itemFormModel.isValid()) {
            itemsRepository.insertItem(uiState.itemFormModel.toItem())
        }
    }

}

