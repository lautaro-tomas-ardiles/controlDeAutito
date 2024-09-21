package com.example.controldeautito

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.controldeautito.Screens.MainScreen
import com.example.controldeautito.ui.theme.ControlDeAutitoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ControlDeAutitoTheme {
                MainScreen()
            }
        }
    }
}
