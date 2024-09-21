package com.example.controldeautito.Server

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class UrlsViewModel : ViewModel() {
    //declaracion de la url
    var urls by mutableStateOf("http://192.168.1.101/")
        private set

    //declaracion del estado del robot
    var state by mutableStateOf("s")
        private set

    //declaracion del tipo de conexion
    var typeOfConnection by mutableStateOf(true)
        private set

    // Función para actualizar el valor de urlString
    fun updateUrlString(newUrl: String) {
        urls = newUrl
    }

    //funcion para actualizar el estado del robot
    fun updateState(newState: String) {
        state = newState
    }

    //funcion para actualizar el tipo de conexion
    fun updateTypeOfConnection(newTypeOfConnection: Boolean) {
        typeOfConnection = newTypeOfConnection
    }

}
