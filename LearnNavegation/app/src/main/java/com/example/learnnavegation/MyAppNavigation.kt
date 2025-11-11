package com.example.learnnavegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun MyAppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.screenA
    ) {
        composable(Routes.screenA) {
            ScreenA(navController)
        }
        composable(Routes.screenB + "/{name}") {
            val name = it.arguments?.getString("name") ?: "No name"
            ScreenB(navController, name)
        }
        // 🔧 Ruta corregida
        composable(Routes.screenC + "/{name}") {
            val name = it.arguments?.getString("name")
            ScreenC(name ?: "No name")
        }
    }
}
