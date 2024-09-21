package com.example.controldeautito.Server

import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

fun serverConnect(viewModel: UrlsViewModel) {
    // Accedes al valor de las variables del ViewModel
    val currentUrl = viewModel.urls
    val currentState = viewModel.state
    val currentTypeOfConnection = viewModel.typeOfConnection

    if(currentTypeOfConnection) {
        // Codificar el parámetro "a" antes de añadirlo a la URL
        val encodedParam = URLEncoder.encode(currentState, "UTF-8")
        val urlWithParam =
            "$currentUrl?param=$encodedParam"  // Añades el parámetro codificado a la URL

        val url = URL(urlWithParam)

        // Abres la conexión
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"  // Usar método GET para la solicitud

        try {
            // Comprueba la respuesta del servidor
            val responseCode = connection.responseCode
            println("Response Code: $responseCode")

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lee la respuesta del servidor si es necesario
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                println("Response: $response")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            // Desconectar la conexión
            connection.disconnect()
        }
    }else{
        val url = URL(currentUrl)

        // Abres la conexión
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"  // Usar método POST
        connection.doOutput = true  // Permitir enviar datos en el cuerpo

        try {
            // Codificar el parámetro "a"
            val encodedParam = URLEncoder.encode("a", "UTF-8")
            val params = "param=$encodedParam"  // Formatear el string con el parámetro

            // Configurar el Content-Type
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")

            // Enviar el parámetro en el cuerpo de la solicitud
            val outputStreamWriter = OutputStreamWriter(connection.outputStream)
            outputStreamWriter.write(params)
            outputStreamWriter.flush()
            outputStreamWriter.close()

            // Comprobar la respuesta del servidor
            val responseCode = connection.responseCode
            println("Response Code: $responseCode")

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Procesar la respuesta si es necesario
                val response = connection.inputStream.bufferedReader().use { it.readText() }
                println("Response: $response")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            // Desconectar la conexión
            connection.disconnect()
        }
    }
}