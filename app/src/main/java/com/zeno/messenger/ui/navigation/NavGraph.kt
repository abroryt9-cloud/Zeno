package com.zeno.messenger.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zeno.messenger.ui.screens.EnterPhoneScreen
import com.zeno.messenger.ui.screens.MainScreen
import com.zeno.messenger.ui.screens.VerifyCodeScreen

object Routes {
    const val EnterPhone = "enter_phone"
    const val VerifyCode = "verify_code"
    const val Main = "main"
}

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Routes.EnterPhone,
    ) {
        composable(Routes.EnterPhone) {
            EnterPhoneScreen(navController = navController)
        }
        composable(Routes.VerifyCode) {
            VerifyCodeScreen(navController = navController)
        }
        composable(Routes.Main) {
            MainScreen(navController = navController)
        }
    }
}

