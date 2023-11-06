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

package com.example.inventory.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.inventory.ui.home.HomeScreen
import com.example.inventory.ui.home.HomeViewModel
import com.example.inventory.ui.item.details.ItemDetailsScreen
import com.example.inventory.ui.item.details.ItemDetailsViewModel
import com.example.inventory.ui.item.edit.ItemEditScreen
import com.example.inventory.ui.item.edit.ItemEditViewModel
import com.example.inventory.ui.item.entry.ItemEntryScreen
import com.example.inventory.ui.item.entry.ItemEntryViewModel

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                navigateToItemEntry = { navController.navigate(ItemEntryDestination.route) },
                navigateToItemDetails = { id->
                    navController.navigate("${ItemDetailsDestination.route}/${id}")
                },
                viewModel = viewModel
            )
        }
        composable(route = ItemEntryDestination.route) {
            val viewModel: ItemEntryViewModel = hiltViewModel()
            ItemEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                viewModel = viewModel
            )
        }
        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            val viewModel: ItemDetailsViewModel = hiltViewModel()
            ItemDetailsScreen(
                navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() },
                viewModel = viewModel
            )
        }
        composable(
            route = ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            val viewModel: ItemEditViewModel = hiltViewModel()
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                viewModel = viewModel
            )
        }
    }
}