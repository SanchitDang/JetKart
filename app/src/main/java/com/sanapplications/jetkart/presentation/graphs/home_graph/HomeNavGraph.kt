package com.sanapplications.jetkart.presentation.graphs.home_graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sanapplications.jetkart.presentation.graphs.Graph
import com.sanapplications.jetkart.presentation.graphs.detail_graph.DetailScreen
import com.sanapplications.jetkart.presentation.graphs.detail_graph.detailNavGraph
import com.sanapplications.jetkart.presentation.screens.dashboard_screen.component.DashboardScreen
import com.sanapplications.jetkart.presentation.screens.favourite_screen.component.FavouriteScreen
import com.sanapplications.jetkart.presentation.screens.profile_screen.component.ProfileScreen

@Composable
fun HomeNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = ShopHomeScreen.DashboardScreen.route
    ) {
        composable(ShopHomeScreen.DashboardScreen.route) {
            DashboardScreen(
                onItemClick = { productId ->
                    navHostController.navigate(DetailScreen.ProductDetailScreen.route + "/${productId}")
                }
            )
        }
        composable(ShopHomeScreen.FavouriteScreen.route) {
            FavouriteScreen(
                productViewModel = hiltViewModel(),
                onItemClick = { productId ->
                    navHostController.navigate(DetailScreen.ProductDetailScreen.route + "/${productId}")
                }
            )
        }
        composable(ShopHomeScreen.ProfileScreen.route) {
            ProfileScreen(
                navController = navHostController
            )
        }
        //detail graph
        detailNavGraph(navController = navHostController)
    }
}