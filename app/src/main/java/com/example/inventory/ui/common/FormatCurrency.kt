package com.example.inventory.ui.common

import java.text.NumberFormat

fun formatCurrency(value: Double): String =
    NumberFormat.getCurrencyInstance().format(value)