package com.sanapplications.jetkart.presentation.graphs.detail_graph

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.sanapplications.jetkart.common.Constrains
import com.sanapplications.jetkart.domain.model.ProductModel
import com.sanapplications.jetkart.presentation.graphs.Graph
import com.sanapplications.jetkart.presentation.screens.cart_screen.component.CartScreen
import com.sanapplications.jetkart.presentation.screens.notification_screen.component.NotificationScreen
import com.sanapplications.jetkart.presentation.screens.product_detail_screen.ProductDetailViewModel
import com.sanapplications.jetkart.presentation.screens.product_detail_screen.component.ProductDetailScreen

fun NavGraphBuilder.detailNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailScreen.ProductDetailScreen.route + "/{${Constrains.PRODUCT_ID_PARAM}}"
    ) {
        composable(DetailScreen.CartScreen.route) {
            CartScreen()
        }
        composable(DetailScreen.NotificationScreen.route) {
            NotificationScreen()
        }
        composable(DetailScreen.ProductDetailScreen.route + "/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: return@composable
            val productDetailViewModel: ProductDetailViewModel = hiltViewModel()
            val state = productDetailViewModel.state.value

            // Load product details once for the given productId
            LaunchedEffect(productId) {
                productDetailViewModel.getProductDetail(productId)
            }

            // Display loading indicator, error, or product details based on state
            ProductDetailScreen(
                product = state.productDetail,
                isLoading = state.isLoading,
                errorMessage = state.errorMessage
            ) {
                navController.popBackStack()
            }
        }
    }
}