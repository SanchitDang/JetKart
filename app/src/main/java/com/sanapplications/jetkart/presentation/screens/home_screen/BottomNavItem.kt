package com.sanapplications.jetkart.presentation.screens.home_screen

import com.sanapplications.jetkart.R
import com.sanapplications.jetkart.presentation.graphs.home_graph.ShopHomeScreen

sealed class BottomNavItem(val tittle: String, val icon: Int, val route: String) {
    object HomeNav : BottomNavItem(
        tittle = "Home",
        icon = R.drawable.shop_icon,
        route = ShopHomeScreen.DashboardScreen.route
    )

    object FavouriteNav : BottomNavItem(
        tittle = "Favourite",
        icon = R.drawable.heart_icon,
        route = ShopHomeScreen.FavouriteScreen.route
    )

    object ProfileNav : BottomNavItem(
        tittle = "Profile",
        icon = R.drawable.user_icon,
        route = ShopHomeScreen.ProfileScreen.route
    )
}
