package com.example.mitalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mitalk.ui.chat.ChatRoomScreen
import com.example.mitalk.ui.chat_type.ChatTypeScreen
import com.example.mitalk.ui.main.MainScreen
import com.example.mitalk.ui.question.QuestionScreen
import com.example.mitalk.ui.record.RecordDetailScreen
import com.example.mitalk.ui.record.RecordScreen
import com.example.mitalk.ui.splash.SplashScreen
import com.example.mitalk.util.theme.base.MitalkTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
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

    NavHost(navController = navController, startDestination = AppNavigationItem.Splash.route) {
        composable(AppNavigationItem.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(AppNavigationItem.Main.route) {
            MainScreen(navController = navController)
        }

        composable(AppNavigationItem.Question.route) {
            QuestionScreen(navController = navController)
        }

        composable(AppNavigationItem.ChatType.route) {
            ChatTypeScreen(navController = navController)
        }

        composable(
            route = AppNavigationItem.ChatRoom.route
                    + DeepLinkKey.CHAT_TYPE + "{${DeepLinkKey.CHAT_TYPE}}"
                    + DeepLinkKey.ROOM_ID + "{${DeepLinkKey.ROOM_ID}}",
            arguments = listOf(
                navArgument(DeepLinkKey.CHAT_TYPE) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(DeepLinkKey.ROOM_ID) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val type = it.arguments?.getString(DeepLinkKey.CHAT_TYPE) ?: ""
            val roomId = it.arguments?.getString(DeepLinkKey.ROOM_ID) ?: ""

            ChatRoomScreen(
                navController = navController,
                type = type,
                roomId = roomId
            )
        }

        composable(AppNavigationItem.Record.route) {
            RecordScreen(navController = navController)
        }

        composable(
            route = AppNavigationItem.RecordDetail.route
                    + DeepLinkKey.HEADER + "{${DeepLinkKey.HEADER}}",
            arguments = listOf(
                navArgument(DeepLinkKey.HEADER) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val header = it.arguments?.getString(DeepLinkKey.HEADER) ?: ""

            RecordDetailScreen(
                navController = navController,
                header = header
            )
        }
    }
}

sealed class AppNavigationItem(val route: String) {
    object Splash : AppNavigationItem("Splash")

    object Main : AppNavigationItem("Main")

    object Question : AppNavigationItem("Question")

    object ChatType : AppNavigationItem("ChatType")

    object ChatRoom : AppNavigationItem("ChatRoom")

    object Record : AppNavigationItem("Record")

    object RecordDetail : AppNavigationItem("RecordDetail")
}

object DeepLinkKey {
    const val HEADER = "header"
    const val CHAT_TYPE = "chatType"
    const val ROOM_ID = "roomId"
}
