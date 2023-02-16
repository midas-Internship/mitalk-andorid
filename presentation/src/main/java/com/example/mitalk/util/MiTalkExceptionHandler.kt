package com.example.mitalk.util

import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import com.example.domain.exception.NeedLoginException
import com.example.mitalk.AppNavigationItem
import com.example.mitalk.MainActivity
import com.example.mitalk.vm.TokenRefreshViewModel

class MiTalkExceptionHandler(
    private val mainActivity: MainActivity,
    private val navController: NavController,
    private val tokenRefreshViewModel: TokenRefreshViewModel,
): Thread.UncaughtExceptionHandler {

    override fun uncaughtException(t: Thread, e: Throwable) {
        when (e) {
            is NeedLoginException -> {
                tokenRefreshObserver(
                    mainActivity = mainActivity,
                    navController = navController,
                    tokenRefreshViewModel = tokenRefreshViewModel,
                )
                tokenRefreshViewModel.tokenRefresh()
            }
        }
    }
}

fun tokenRefreshObserver(
    mainActivity: MainActivity,
    tokenRefreshViewModel: TokenRefreshViewModel,
    navController: NavController
) {
    mainActivity.repeatOnStarted {
        tokenRefreshViewModel.token.collect {
            when (it) {
                is TokenRefreshViewModel.Event.Success -> {
                    Toast.makeText(mainActivity, "토큰 만료 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }
                is TokenRefreshViewModel.Event.Fail -> {
                    Toast.makeText(mainActivity, "토큰 만료 다시 로그인해주세요", Toast.LENGTH_SHORT).show()
                    navController.navigate(
                        route = AppNavigationItem.Splash.route
                    ) {
                        popUpTo(0)
                    }
                }
            }
        }
    }
}