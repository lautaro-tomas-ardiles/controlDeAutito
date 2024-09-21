@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.controldeautito.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.controldeautito.Server.serverConnect
import com.example.controldeautito.Server.UrlsViewModel
import com.example.controldeautito.ui.theme.*

/**
* @author Lautaro Ardiles
*/

@Composable
fun MainTopBar(viewModel: UrlsViewModel) {
    var urlString by remember { mutableStateOf("")}
    var isGet by remember { mutableStateOf(true)}

    TopAppBar(
        title = {
            Text(
                text = "Control de Autito",
                color = Color.White,
                fontSize = 30.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Brown//color del contenido de la barra
        ),
        navigationIcon = {
            Switch(
                checked = isGet,
                onCheckedChange = {isGet = it}
            )
        },
        actions = {//entrada de texto al final de la top bar
            OutlinedTextField(
                value = urlString,
                onValueChange = {urlString = it },
                label = {
                    Text(
                        text = "ingrese la url...",
                        color = Color.LightGray,
                        fontSize = 20.sp
                    )
                },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,//color del texto
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Brown,//color del fondo
                    unfocusedContainerColor = Brown,
                    focusedIndicatorColor = Brown,//color del borde
                    unfocusedIndicatorColor = Brown,
                    focusedLabelColor = Color.White,//color de la etiqueta
                    unfocusedLabelColor = Color.White,
                ),
                trailingIcon = {
                    Button(//boton al final de la entrada de texto
                        onClick = {
                            viewModel.updateUrlString(urlString)//actualiza la url
                            viewModel.updateTypeOfConnection(isGet)
                            serverConnect(viewModel)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Orange,
                            contentColor = Black
                        )
                    ) {
                        Text(text = "actualizar")
                    }
                }
            )
        }
    )
}

@Composable
fun MainControls(viewModel: UrlsViewModel) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(
                onClick = {
                    viewModel.updateState("f")
                    serverConnect(viewModel)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange,
                    contentColor = Black
                )
            ) {
                Text(text = "adelante")
            }

            Spacer(modifier = Modifier.padding(5.dp))

            Button(
                onClick = {
                    viewModel.updateState("b")
                    serverConnect(viewModel)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange,
                    contentColor = Black
                )
            ) {
                Text(text = "atras")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    viewModel.updateState("s")
                    serverConnect(viewModel)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange,
                    contentColor = Black
                )
            ) {
                Text(text = "parar")
            }

            Spacer(modifier = Modifier.padding(horizontal = 90.dp))

            Button(
                onClick = {
                    viewModel.updateState("l")
                    serverConnect(viewModel)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange,
                    contentColor = Black
                )
            ) {
                Text(text = "izquierda")
            }

            Spacer(modifier = Modifier.padding(horizontal = 5.dp))

            Button(
                onClick = {
                    viewModel.updateState("r")
                    serverConnect(viewModel)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange,
                    contentColor = Black
                )
            ) {
                Text(text = "derecha")
            }
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            MainTopBar(UrlsViewModel())//declaracioncion de la top bar
                 },
        content = { innerPadding ->//se establese padding interno de la pantalla
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                MainControls(UrlsViewModel())
            }
        },
        containerColor = Black//color del fondo de la pantalla
    )
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun Preview() {
    ControlDeAutitoTheme {
        MainScreen()
    }
}