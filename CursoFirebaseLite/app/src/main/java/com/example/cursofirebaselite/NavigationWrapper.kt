package com.example.cursofirebaselite

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cursofirebaselite.Presentation.home.HomeScreen
import com.example.cursofirebaselite.Presentation.initial.InitialScreen
import com.example.cursofirebaselite.Presentation.login.LoginScreen
import com.example.cursofirebaselite.Presentation.signup.SingUpScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

@Composable

fun NavigationWrapper(navHostController: NavHostController, auth: FirebaseAuth) {

    NavHost(navController = navHostController, startDestination = "initial") {
        composable("initial") {
            InitialScreen(
                navigateToLogin = { navHostController.navigate("LogIn") },
                navigateToSignUp = { navHostController.navigate("SignUp") }
            )
        }
        composable("login") {
            LoginScreen(auth){navHostController.navigate("home")}
        }
        composable("signup") {
            SingUpScreen(auth)
        }
        composable("home") {
            HomeScreen()
        }
    }
}