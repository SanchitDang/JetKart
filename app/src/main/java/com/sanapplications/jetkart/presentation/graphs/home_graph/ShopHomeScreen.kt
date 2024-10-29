package com.sanapplications.jetkart.presentation.graphs.home_graph

sealed class ShopHomeScreen(val route: String) {
    object DashboardScreen : ShopHomeScreen("dashboard_screen")
    object ProfileScreen : ShopHomeScreen("profile_screen")
    object FavouriteScreen : ShopHomeScreen("favourite_screen")
}
