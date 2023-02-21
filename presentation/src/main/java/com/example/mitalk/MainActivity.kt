package com.example.mitalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mitalk.ui.chat.ChatRoomScreen
import com.example.mitalk.ui.chat.ChatTypeScreen
import com.example.mitalk.ui.main.MainScreen
import com.example.mitalk.ui.question.QuestionScreen
import com.example.mitalk.ui.record.RecordDetailScreen
import com.example.mitalk.ui.record.RecordScreen
import com.example.mitalk.ui.setting.SettingScreen
import com.example.mitalk.ui.splash.SplashScreen
import com.example.mitalk.util.MiTalkExceptionHandler
import com.example.mitalk.util.theme.base.MitalkTheme
import com.example.mitalk.vm.TokenRefreshViewModel
import com.example.mitalk.vm.chat.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tokenRefreshViewModel: TokenRefreshViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MitalkTheme {
                BaseApp(navController = navController)

                Thread.setDefaultUncaughtExceptionHandler(
                    MiTalkExceptionHandler(
                        mainActivity = this,
                        navController = navController,
                        tokenRefreshViewModel = tokenRefreshViewModel
                    )
                )
            }
        }
    }
}

@Composable
fun BaseApp(navController: NavHostController) {
    val chatViewModel = viewModel<ChatViewModel>()

    LaunchedEffect(chatViewModel) {
        chatViewModel.getAccessToken()
        chatViewModel.setChatTypeSocket()
    }

    NavHost(navController = navController, startDestination = AppNavigationItem.Splash.route) {
        composable(AppNavigationItem.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(AppNavigationItem.Main.route) {
            MainScreen(navController = navController, chatViewModel = chatViewModel)
        }

        composable(AppNavigationItem.Question.route) {
            QuestionScreen(navController = navController)
        }

        composable(AppNavigationItem.ChatType.route) {
            ChatTypeScreen(navController = navController, vm = chatViewModel)
        }

        composable(AppNavigationItem.ChatRoom.route) {
            ChatRoomScreen(
                navController = navController,
                vm = chatViewModel
            )
        }

        composable(AppNavigationItem.Record.route) {
            RecordScreen(navController = navController)
        }

        composable(
            route = AppNavigationItem.RecordDetail.route
                    + DeepLinkKey.HEADER_ID + "{${DeepLinkKey.HEADER_ID}}"
                    + DeepLinkKey.RECORD_ID + "{${DeepLinkKey.RECORD_ID}}",
            arguments = listOf(
                navArgument(DeepLinkKey.HEADER_ID) {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument(DeepLinkKey.RECORD_ID) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val headerId = it.arguments?.getInt(DeepLinkKey.HEADER_ID) ?: R.string.function_question
            val recordId = it.arguments?.getString(DeepLinkKey.RECORD_ID) ?: ""

            RecordDetailScreen(
                navController = navController,
                headerId = headerId,
                recordId = recordId
            )
        }

        composable(AppNavigationItem.Setting.route) {
            SettingScreen(navController = navController)
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

    object Setting : AppNavigationItem("Setting")
}

object DeepLinkKey {
    const val HEADER_ID = "headerId"
    const val RECORD_ID = "recordId"
}
