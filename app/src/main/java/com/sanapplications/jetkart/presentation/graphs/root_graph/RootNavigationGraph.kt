package com.sanapplications.jetkart.presentation.graphs.root_graph

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sanapplications.jetkart.domain.model.AuthViewModel
import com.sanapplications.jetkart.presentation.graphs.Graph
import com.sanapplications.jetkart.presentation.graphs.auth_graph.authNavGraph
import com.sanapplications.jetkart.presentation.screens.home_screen.component.HomeScreen

@Composable
fun RootNavigationGraph(navHostController: NavHostController, context: Context, authViewModel: AuthViewModel) {
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION,
    ) {
        authNavGraph(navHostController, context, authViewModel)
        composable(route = Graph.HOME) {
            HomeScreen()
        }
    }
}