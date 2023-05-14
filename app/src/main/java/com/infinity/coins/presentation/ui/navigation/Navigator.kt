package com.infinity.coins.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.infinity.coins.presentation.ui.screen.Notifications
import com.infinity.coins.presentation.ui.screen.Statistics
import com.infinity.coins.presentation.ui.screen.User

@Composable
fun Navigator(navController: NavHostController, bottomPadding: Dp = Dp(0f)) {
    NavHost(navController = navController, startDestination = Screen.TimeLine.route) {
        composable(route = Screen.Login.route) {

        }
        composable(route = Screen.TimeLine.route) {

        }
        composable(route = Screen.Statistics.route) {
            Statistics()
        }
        composable(route = Screen.Notifications.route) {
            Notifications()
        }
        composable(route = Screen.User.route) {
            User()
        }
    }
}