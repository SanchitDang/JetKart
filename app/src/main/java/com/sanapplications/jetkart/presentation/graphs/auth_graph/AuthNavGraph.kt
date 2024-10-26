package com.sanapplications.jetkart.presentation.graphs.auth_graph

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sanapplications.jetkart.domain.model.AuthViewModel
import com.sanapplications.jetkart.presentation.graphs.Graph
import com.sanapplications.jetkart.presentation.screens.forget_password_screen.component.ForgetPasswordScreen
import com.sanapplications.jetkart.presentation.screens.on_boarding_screen.component.SplashScreen
import com.sanapplications.jetkart.presentation.screens.otp_screen.component.OTPScreen
import com.sanapplications.jetkart.presentation.screens.sign_in_screen.component.LoginScreen
import com.sanapplications.jetkart.presentation.screens.sign_success_screen.component.SignInSuccessScreen
import com.sanapplications.jetkart.presentation.screens.sign_up_screen.component.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController, context: Context) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.OnBoardingScreen.route
    ) {
        composable(AuthScreen.OnBoardingScreen.route) {
            SplashScreen(navController)
        }
        composable(AuthScreen.SignInScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(AuthScreen.ForgetPasswordScreen.route) {
            ForgetPasswordScreen(navController = navController)
        }
        composable(AuthScreen.OTPScreen.route) {
            OTPScreen(navController = navController)
        }
        composable(AuthScreen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }
        composable(AuthScreen.SignInSuccess.route) {
            SignInSuccessScreen(navController = navController)
        }
    }
}