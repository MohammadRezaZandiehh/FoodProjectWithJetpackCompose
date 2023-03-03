package com.example.foodprojectwithjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.example.foodprojectwithjetpackcompose.screens.CookingScreen
import com.example.foodprojectwithjetpackcompose.ui.theme.FoodProjectWithJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodProjectWithJetpackComposeTheme {
                Surface {
                    CookingScreen()
                }

            }
        }
    }
}