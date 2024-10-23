package com.sanapplications.jetkart.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import com.sanapplications.jetkart.domain.model.AuthViewModel
import com.sanapplications.jetkart.presentation.graphs.root_graph.RootNavigationGraph
import com.sanapplications.jetkart.presentation.ui.theme.JetKartTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authViewModel : AuthViewModel by viewModels()

        setContent {
            JetKartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ShowScreen(LocalContext.current, authViewModel)
                }
            }
        }
    }
}

@Composable
private fun ShowScreen(context: Context, authViewModel: AuthViewModel) {
    val navHostController = rememberNavController()
    RootNavigationGraph(navHostController = navHostController, context = context, authViewModel = authViewModel)
}

