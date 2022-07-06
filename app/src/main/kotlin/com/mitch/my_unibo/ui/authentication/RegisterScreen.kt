package com.mitch.my_unibo.ui.authentication

import android.util.Patterns
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
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

@Composable
fun RegisterScreen(
    auth: AuthenticatorViewModel = hiltViewModel(),
    navController: NavHostController,
    systemController: SystemUiController
) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val errorVisibility = remember { mutableStateOf(false) }
    val firstNameErrorVisibility = remember { mutableStateOf(false) }
    val lastNameErrorVisibility = remember { mutableStateOf(false) }

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
            Title(text = stringResource(R.string.register))

            FirstNameField(
                firstName = firstName,
                focusManager = focusManager,
                errorVisibility = firstNameErrorVisibility
            )
            LastNameField(
                lastName = lastName,
                focusManager = focusManager,
                errorVisibility = lastNameErrorVisibility
            )
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
            RegisterButton {
                focusManager.clearFocus()

                if (firstName.value.isBlank()) {
                    firstNameErrorVisibility.value = true
                }
                if (lastName.value.isBlank()) {
                    lastNameErrorVisibility.value = true
                }
                if (
                    email.isBlank()
                    || password.isBlank()
                ) {
                    errorVisibility.value = true
                }

                if (
                    firstName.value.isNotBlank()
                    && lastName.value.isNotBlank()
                    && email.isNotBlank()
                    && password.isNotBlank()
                    && !auth.isEmailTaken(email.trim())
                    && Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
                ) {
                    auth.registerStudent(
                        firstName = firstName.value,
                        lastName = lastName.value,
                        email = email,
                        password = password
                    )
                    // add taxes
                    navController.navigate(Screen.Login.route)
                } else {
                    errorVisibility.value = true
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                GoToLoginButton {
                    navController.navigate(Screen.Login.route)
                }
            }
        }
    }
}

@Composable
private fun FirstNameField(
    firstName: MutableState<String>,
    focusManager: FocusManager,
    errorVisibility: MutableState<Boolean>
) {
    Column(
        modifier = Modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        OutlinedTextField(
            value = firstName.value,
            onValueChange = { firstName.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("First name") },
            placeholder = { Text(text = "first name") },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            singleLine = true
        )
        if (errorVisibility.value) {
            FieldCannotBeEmpty()
        }
    }
}

@Composable
private fun LastNameField(
    lastName: MutableState<String>,
    focusManager: FocusManager,
    errorVisibility: MutableState<Boolean>
) {
    Column(
        modifier = Modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        OutlinedTextField(
            value = lastName.value,
            onValueChange = { lastName.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Last name") },
            placeholder = { Text(text = "last name") },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            singleLine = true
        )
        if (errorVisibility.value) {
            FieldCannotBeEmpty()
        }
    }
}

@Composable
private fun FieldCannotBeEmpty() {
    Text(
        text = "field cannot be empty",
        color = Color.Red
    )
}

@Composable
private fun RegisterButton(
    onClick: () -> Unit
) {
    PrimaryButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Text(
            text = stringResource(R.string.register).uppercase(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun GoToLoginButton(
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        contentPadding = PaddingValues(start = 0.dp, end = 15.dp)
    ) {
        Text(text = stringResource(R.string.login))
    }
}
