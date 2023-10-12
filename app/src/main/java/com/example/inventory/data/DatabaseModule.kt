package com.example.inventory.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun inventoryDatabase(
        @ApplicationContext context: Context
    ): InventoryDatabase = InventoryDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun itemDao(database: InventoryDatabase): ItemDao = database.itemDao()
}