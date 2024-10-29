package com.sanapplications.jetkart.presentation.graphs.auth_graph

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sanapplications.jetkart.presentation.graphs.Graph
import com.sanapplications.jetkart.presentation.screens.auth_screen.forget_password_screen.component.ForgetPasswordScreen
import com.sanapplications.jetkart.presentation.screens.auth_screen.on_boarding_screen.component.SplashScreen
import com.sanapplications.jetkart.presentation.screens.auth_screen.otp_screen.component.OTPScreen
import com.sanapplications.jetkart.presentation.screens.auth_screen.sign_in_screen.component.LoginScreen
import com.sanapplications.jetkart.presentation.screens.auth_screen.success_screen.component.SignInSuccessScreen
import com.sanapplications.jetkart.presentation.screens.auth_screen.success_screen.component.SignUpSuccessScreen
import com.sanapplications.jetkart.presentation.screens.auth_screen.sign_up_screen.component.SignUpScreen

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
        composable(AuthScreen.SignUpSuccess.route) {
            SignUpSuccessScreen(navController = navController)
        }
    }
}