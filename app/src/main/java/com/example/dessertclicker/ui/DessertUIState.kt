package com.example.dessertclicker.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.dessertclicker.data.Datasource.dessertList

data class DessertUIState(
    var revenue: Int = 0,
    var dessertsSold: Int = 0,

    val currentDessertIndex: Int = 0,

    var currentDessertPrice: Int = dessertList[currentDessertIndex].price,

    var currentDessertImageId: Int = dessertList[currentDessertIndex].imageId
)
