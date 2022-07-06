package com.mitch.my_unibo.ui.authentication

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.mitch.my_unibo.R
import com.mitch.my_unibo.features.authentication.AuthenticatorViewModel
import com.mitch.my_unibo.ui.authentication.components.EmailField
import com.mitch.my_unibo.ui.authentication.components.PasswordField
import com.mitch.my_unibo.ui.custom.buttons.PrimaryButton
import com.mitch.my_unibo.ui.custom.text.Title
import com.mitch.my_unibo.ui.navigation.Screen
import com.mitch.my_unibo.ui.theme.custom.padding
import com.mitch.my_unibo.ui.theme.custom.spacing
import com.mitch.my_unibo.ui.theme.isThemeDark
import com.mitch.my_unibo.ui.util.SnackbarController

@Composable
fun LoginScreen(
    auth: AuthenticatorViewModel = hiltViewModel(),
    navController: NavHostController,
    systemController: SystemUiController,
    scaffoldState: ScaffoldState,
    snackbarController: SnackbarController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val errorVisibility = remember { mutableStateOf(false) }

    systemController.setStatusBarColor(
        color = MaterialTheme.colors.background,
        darkIcons = !isThemeDark()
    )

    systemController.setNavigationBarColor(
        color = MaterialTheme.colors.background,
        darkIcons = !isThemeDark()
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = padding.medium,
                top = padding.medium,
                end = padding.medium,
                bottom = 0.dp
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.large)
        ) {
            Title(text = stringResource(R.string.login))

            EmailField(
                email = email,
                onEmailChange = {
                    email = it
                },
                focusManager = focusManager,
                errorVisibility = errorVisibility
            )
            PasswordField(
                password = password,
                onPasswordChange = {
                    password = it
                },
                passwordVisibility = passwordVisibility,
                focusManager = focusManager,
                errorVisibility = errorVisibility
            )

            // login button
            LoginButton {
                focusManager.clearFocus()
                if (
                    email.isBlank()
                    || password.isBlank()
                ) {
                    errorVisibility.value = true
                }

                if (
                    auth.checkEmailAndPassword(
                        email.trim(),
                        password.trim()
                    )
                    && Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
                ) {
                    auth.loginStudent(email.trim())

                    // so when you press back button, it does not go to login again, but just closes the app
                    navController.popBackStack()
                    navController.navigate(Screen.Courses.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                } else {
                    errorVisibility.value = true
                }
            }

            // row for sign up and forgot password
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                GoToRegisterButton {
                    navController.navigate(Screen.Register.route)
                }
                /*ForgotPasswordButton {
                    navController.navigate(Screen.ForgotPassword.route)
                }*/
            }
        }
    }
}

@Composable
private fun LoginButton(
    onClick: () -> Unit
) {
    PrimaryButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Text(
            text = stringResource(R.string.login).uppercase(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun GoToRegisterButton(
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        contentPadding = PaddingValues(start = 0.dp, end = 15.dp)
    ) {
        Text(text = stringResource(R.string.register))
    }
}

@Composable
private fun ForgotPasswordButton(
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        contentPadding = PaddingValues(start = 15.dp, end = 0.dp)
    ) {
        Text(text = stringResource(R.string.forgot_password), color = Color.Gray)
    }
}

@Preview
@Composable
fun LoginScreenPreview() {/*
    LoginScreen(navController = rememberNavController())*/
}
