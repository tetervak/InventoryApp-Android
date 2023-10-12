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

package com.example.inventory.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(DelicateCoroutinesApi::class)
class OfflineItemsRepository(
    private val itemDao: ItemDao,
    private val externalScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) : ItemsRepository {

    @Inject
    constructor(itemDao: ItemDao) : this(itemDao, GlobalScope, Dispatchers.IO)

    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)

    override suspend fun insertItem(item: Item) {
        externalScope.launch(dispatcher) { itemDao.insert(item) }.join()
    }

    override suspend fun deleteItem(item: Item) {
        externalScope.launch(dispatcher) { itemDao.delete(item) }.join()
    }

    override suspend fun updateItem(item: Item) {
        externalScope.launch(dispatcher) { itemDao.update(item) }.join()
    }
}
