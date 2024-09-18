package com.example.dessertclicker.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.R
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.determineDessertToShow
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DessertUIState())
    val uiState: StateFlow<DessertUIState> = _uiState.asStateFlow()


        fun onDessertClicked() {
            _uiState.update { currentState ->
                val dessertsSold = currentState.dessertsSold+1
                val nextIdNumber = determineIndex(dessertsSold)
                currentState.copy(
                    revenue = currentState.revenue + currentState.currentDessertPrice,
                    dessertsSold = dessertsSold,
                    currentDessertIndex = nextIdNumber,
                    currentDessertPrice = dessertList[nextIdNumber].price,
                    currentDessertImageId = dessertList[nextIdNumber].imageId,

                )

            }

            // Update the revenue
        //    revenue += currentDessertPrice
        //    dessertsSold++

      //     val dessertToShow = determineDessertToShow(desserts, dessertUIState.dessertsSold)
            // Show the next dessert
      //      currentDessertImageId = dessertToShow.imageId
     //       currentDessertPrice = dessertToShow.pri


        }

    private fun determineIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
        for (index in dessertList.indices) {
            if (dessertsSold >= dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more
                // desserts, you'll start producing more expensive desserts as determined by
                // startProductionAmount. We know to break as soon as we see a dessert who's
                // "startProductionAmount" is greater than the amount sold.
                break
            }
        }
        return dessertIndex
    }


}