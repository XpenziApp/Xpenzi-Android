package com.infinity.coins.presentation.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen (val route: String) {
    object Login : Screen("screen_login")
    object OTP : Screen("screen_otp")
    object Home : Screen("screen_home")
    object TimeLine : Screen("screen_timeline")
    object Statistics : Screen("screen_statistics")
    object Notifications : Screen("screen_notifications")
    object User : Screen("screen_settings")
}

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)

val bottomNavItems = listOf(
    BottomNavItem(
        name = "Timeline",
        route = "screen_timeline",
        icon = Icons.Rounded.Star,
    ),
    BottomNavItem(
        name = "Statistics",
        route = "screen_statistics",
        icon = Icons.Rounded.AddCircle,
    ),
    BottomNavItem(
        name = "Notifications",
        route = "screen_notifications",
        icon = Icons.Rounded.Notifications,
    ),
    BottomNavItem(
        name = "Settings",
        route = "screen_settings",
        icon = Icons.Rounded.Settings,
    )
)