package com.example.mitalk

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mitalk.ui.main.MainScreen
import com.example.mitalk.ui.question.QuestionScreen
import com.example.mitalk.ui.sample.ui.SampleScreen
import com.example.mitalk.ui.splash.SplashScreen
import com.example.mitalk.util.theme.base.MitalkTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MitalkTheme {
                BaseApp()
            }
        }
    }
}

@Composable
fun BaseApp() {
    val navController = rememberNavController()
    val startDestination =
        if (GoogleSignIn.getLastSignedInAccount(LocalContext.current) != null) AppNavigationItem.Main.route else AppNavigationItem.Splash.route
    NavHost(navController = navController, startDestination = startDestination) {
        composable(AppNavigationItem.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(AppNavigationItem.Main.route) {
            MainScreen(navController = navController)
        }

        composable(AppNavigationItem.Question.route) {
            QuestionScreen(navController = navController)
        }
    }
}

sealed class AppNavigationItem(val route: String) {
    object Splash : AppNavigationItem("Splash")

    object Main : AppNavigationItem("Main")

    object Question : AppNavigationItem("Question")
}
