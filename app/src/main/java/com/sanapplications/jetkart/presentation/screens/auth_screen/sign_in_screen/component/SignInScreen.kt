package com.sanapplications.jetkart.presentation.screens.auth_screen.sign_in_screen.component

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sanapplications.jetkart.R
import com.sanapplications.jetkart.presentation.screens.auth_screen.AuthState
import com.sanapplications.jetkart.presentation.screens.auth_screen.AuthViewModel
import com.sanapplications.jetkart.presentation.common.CustomDefaultBtn
import com.sanapplications.jetkart.presentation.common.CustomTextField
import com.sanapplications.jetkart.presentation.common.component.DefaultBackArrow
import com.sanapplications.jetkart.presentation.common.component.ErrorSuggestion
import com.sanapplications.jetkart.presentation.graphs.auth_graph.AuthScreen
import com.sanapplications.jetkart.presentation.ui.theme.PrimaryColor
import com.sanapplications.jetkart.presentation.ui.theme.PrimaryLightColor
import com.sanapplications.jetkart.presentation.ui.theme.TextColor

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var checkBox by remember {
        mutableStateOf(false)
    }
    val emailErrorState = remember {
        mutableStateOf(false)
    }
    val passwordErrorState = remember {
        mutableStateOf(false)
    }

    val authViewModel: AuthViewModel = viewModel()
    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(authState.value) {
        when (authState.value){
            is AuthState. Authenticated -> navController.navigate(AuthScreen.SignInSuccess.route)
            is AuthState. Error -> Toast.makeText(context,
                (authState.value as AuthState. Error).message, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Box(modifier = Modifier.weight(0.7f)) {
                DefaultBackArrow {
                    navController.popBackStack()
                }
            }
            Box(modifier = Modifier.weight(1.0f)) {
                Text(text = "Sign in", color = MaterialTheme.colors.TextColor, fontSize = 18.sp)
            }


        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Welcome Back", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "Sign in with your email or password\nor continue with social media.",
            color = MaterialTheme.colors.TextColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(50.dp))
        CustomTextField(
            placeholder = "example@email.com",
            trailingIcon = R.drawable.mail,
            label = "Email",
            errorState = emailErrorState,
            keyboardType = KeyboardType.Email,
            visualTransformation = VisualTransformation.None,
            onChanged = { newEmail ->
                email = newEmail
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            placeholder = "********",
            trailingIcon = R.drawable.lock,
            label = "Password",
            keyboardType = KeyboardType.Password,
            errorState = passwordErrorState,
            visualTransformation = PasswordVisualTransformation(),
            onChanged = { newPass ->
                password = newPass
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        if (emailErrorState.value) {
            ErrorSuggestion("Please enter valid email address.")
        }
        if (passwordErrorState.value) {
            Row() {
                ErrorSuggestion("Please enter valid password.")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkBox, onCheckedChange = {
                        checkBox = it
                    },
                    colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.PrimaryColor)
                )
                Text(text = "Remember me", color = MaterialTheme.colors.TextColor, fontSize = 14.sp)
            }
            Text(
                text = "Forget Password",
                color = MaterialTheme.colors.TextColor,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier.clickable {
                    navController.navigate(AuthScreen.ForgetPasswordScreen.route)
                }
            )
        }
        CustomDefaultBtn(shapeSize = 50f, btnText = "Continue") {
            //email pattern
            val pattern = Patterns.EMAIL_ADDRESS
            val isEmailValid = pattern.matcher(email.text).matches()
            val isPassValid = password.text.length >= 8
            emailErrorState.value = !isEmailValid
            passwordErrorState.value = !isPassValid
            if (isEmailValid && isPassValid) {
//                navController.navigate(AuthScreen.SignInSuccess.route)
                authViewModel.signIn(email.text, password.text, navController)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 10.dp,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            MaterialTheme.colors.PrimaryLightColor,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google_icon),
                        contentDescription = "Google Login Icon"
                    )
                }
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            MaterialTheme.colors.PrimaryLightColor,
                            shape = CircleShape
                        )
                        .clickable {

                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.twitter),
                        contentDescription = "Twitter Login Icon"
                    )
                }
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            MaterialTheme.colors.PrimaryLightColor,
                            shape = CircleShape
                        )
                        .clickable {

                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook_2),
                        contentDescription = "Facebook Login Icon"
                    )
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Don't have an account? ", color = MaterialTheme.colors.TextColor)
                Text(
                    text = "Sign Up",
                    color = MaterialTheme.colors.PrimaryColor,
                    modifier = Modifier.clickable {
                        navController.navigate(AuthScreen.SignUpScreen.route)
                    })
            }
        }


    }
}


