package com.mitch.my_unibo.ui.authentication.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.mitch.my_unibo.R

@Composable
fun PasswordField(
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisibility: MutableState<Boolean>,
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
            value = password,
            onValueChange = onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") },
            placeholder = { Text(text = "Password") },
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisibility.value = !passwordVisibility.value
                    }
                ) {
                    val eyeSlash = painterResource(id = R.drawable.ic_eye_slash_outlined_black)
                    val eye = painterResource(id = R.drawable.ic_eye_outlined_black)
                    Icon(
                        painter = if (passwordVisibility.value) eyeSlash else eye,
                        contentDescription = if (passwordVisibility.value) "Hide password" else "Show password"
                    )
                }
            },
            visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (errorVisibility.value) Color.Red else Color.Black,
                unfocusedBorderColor = if (errorVisibility.value) Color.Red.copy(0.4f) else Color.Black.copy(
                    0.4f
                )
            )
        )
        if (errorVisibility.value) {
            Text(
                text = stringResource(R.string.email_or_password_incorrect),
                color = Color.Red
            )
        }
    }
}
