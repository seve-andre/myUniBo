package com.mitch.my_unibo.ui.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mitch.my_unibo.R
import com.mitch.my_unibo.datastore.SettingsDataStore
import com.mitch.my_unibo.ui.custom.dialogs.ConfirmationDialog
import com.mitch.my_unibo.ui.custom.text.Title
import com.mitch.my_unibo.ui.navigation.Screen
import com.mitch.my_unibo.ui.theme.AppTheme
import com.mitch.my_unibo.ui.util.AppLanguage
import kotlinx.coroutines.runBlocking

@Composable
fun ProfileSettings(
    navController: NavHostController
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        PreferencesSettings()
        InfoSettings(navController = navController)
    }
}

@Composable
private fun PreferencesSettings() {
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        Title(text = stringResource(R.string.preferences))
        Column(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colors.surface)
        ) {
            LangPreference()
            Divider()
            ThemePreference()
            Divider()
            NotificationsPreference()
        }
    }
}

@Composable
private fun InfoSettings(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        Title(text = "Info")
        Column(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colors.surface)
        ) {
            ClassRoomsInfo {
                navController.navigate("${Screen.SeeSetting.route}/1")
            }
            Divider()
            LibrariesInfo {
                navController.navigate("${Screen.SeeSetting.route}/2")
            }
            Divider()
            ContactsInfo {
                navController.navigate("${Screen.SeeSetting.route}/3")
            }
        }
    }
}

@Composable
private fun LangPreference() {
    val showDialog = remember { mutableStateOf(false) }
    val languageFromDatastore = AppLanguage.selected()
    val selectedLanguage = remember { mutableStateOf(languageFromDatastore) }

    Preference(
        onClickShow = { showDialog.value = true },
        idIconPreference = R.drawable.ic_language,
        iconPreferenceDescription = "preferenza lingua",
        preferenceName = stringResource(R.string.language),
        selectedPreference = selectedLanguage.value.displayLanguage
    )

    val (tempSelectedLanguage, onOptionSelected) = remember { mutableStateOf(selectedLanguage.value) }
    val context = LocalContext.current

    val supportedLangs = AppLanguage.supported()
    val langsFlagsMap = mapOf(
        supportedLangs[0] to Pair(R.drawable.ic_italy, "italy flag"),
        supportedLangs[1] to Pair(R.drawable.ic_uk, "uk flag"),
        supportedLangs[2] to Pair(R.drawable.ic_spain, "spain flag")
    )

    if (showDialog.value) {
        ConfirmationDialog(
            title = "Cambia la lingua",
            onDismissRequest = { showDialog.value = false },
            onConfirmRequest = {
                showDialog.value = false

                if (selectedLanguage.value != tempSelectedLanguage) {
                    selectedLanguage.value = tempSelectedLanguage

                    val appLanguage = when (selectedLanguage.value.toLanguageTag()) {
                        AppLanguage.Italian.languageTag -> AppLanguage.Italian
                        AppLanguage.English.languageTag -> AppLanguage.English
                        AppLanguage.Spanish.languageTag -> AppLanguage.Spanish
                        else -> throw Exception()
                    }
                    AppLanguage.setLanguageAndUpdate(context, appLanguage)
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .selectableGroup(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                langsFlagsMap.forEach { lang ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .selectable(
                                selected = lang.key.displayLanguage == tempSelectedLanguage.displayLanguage,
                                onClick = { onOptionSelected(lang.key) },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = lang.key.displayLanguage == tempSelectedLanguage.displayLanguage,
                            onClick = { onOptionSelected(lang.key) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colors.secondary
                            )
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            Image(
                                painter = painterResource(id = lang.value.first),
                                contentDescription = lang.value.second
                            )
                            Text(text = lang.key.displayLanguage)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ThemePreference() {
    val context = LocalContext.current

    val selectedTheme = AppTheme.selected()
    val themePreference: MutableState<AppTheme> = remember { mutableStateOf(selectedTheme) }

    val themeName = when (themePreference.value) {
        AppTheme.Light -> stringResource(id = R.string.light_theme)
        AppTheme.Dark -> stringResource(id = R.string.dark_theme)
    }

    val showDialog = remember { mutableStateOf(false) }

    Preference(
        onClickShow = { showDialog.value = true },
        idIconPreference = R.drawable.ic_theme,
        iconPreferenceDescription = "preferenza tema",
        preferenceName = stringResource(R.string.theme),
        selectedPreference = themeName
    )

    val (tempSelectedTheme, onOptionSelected) = remember { mutableStateOf(themePreference.value) }
    val supportedThemes = AppTheme.supported()

    if (showDialog.value) {
        ConfirmationDialog(
            title = "Cambia il tema",
            onDismissRequest = { showDialog.value = false },
            onConfirmRequest = {
                showDialog.value = false

                if (themePreference.value != tempSelectedTheme) {
                    themePreference.value = tempSelectedTheme
                    AppTheme.setThemeAndUpdate(context, themePreference.value)
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .selectableGroup(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                supportedThemes.forEach { theme ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .selectable(
                                selected = theme == tempSelectedTheme,
                                onClick = { onOptionSelected(theme) },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = theme == tempSelectedTheme,
                            onClick = { onOptionSelected(theme) }
                        )
                        Text(text = theme.name())
                    }
                }
            }
        }
    }
}

@Composable
private fun NotificationsPreference() {
    val context = LocalContext.current
    val notificationsOn = remember {
        mutableStateOf(
            runBlocking {
                SettingsDataStore(context).areNotificationsOn()
            }
        )
    }

    SwitchPreference(
        idIconPreference = R.drawable.ic_notifications_bell,
        iconPreferenceDescription = "preferenza notifiche",
        preferenceName = stringResource(R.string.notifications),
        checkedState = notificationsOn.value,
        onChange = {
            notificationsOn.value = it
            runBlocking {
                if (it) {
                    SettingsDataStore(context).enableNotifications()
                } else {
                    SettingsDataStore(context).disableNotifications()
                }
            }
        }
    )
}

@Composable
private fun Preference(
    onClickShow: () -> Unit,
    idIconPreference: Int,
    iconPreferenceDescription: String,
    preferenceName: String,
    selectedPreference: String
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth()
            .clickable(onClick = onClickShow),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = idIconPreference),
                contentDescription = iconPreferenceDescription,
                modifier = Modifier.size(28.dp),
                tint = MaterialTheme.colors.onSurface
            )
            Text(text = preferenceName)
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = selectedPreference)
            IconButton(onClick = onClickShow) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_forward),
                    contentDescription = "cambia ${preferenceName.lowercase()}",
                    modifier = Modifier
//                        .rotate(90f)
                        .size(15.dp),
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Composable
private fun SwitchPreference(
    idIconPreference: Int,
    iconPreferenceDescription: String,
    preferenceName: String,
    checkedState: Boolean,
    onChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = idIconPreference),
                contentDescription = iconPreferenceDescription,
                modifier = Modifier.size(28.dp),
                tint = MaterialTheme.colors.onSurface
            )
            Text(text = preferenceName)
        }
        Switch(
            checked = checkedState,
            onCheckedChange = onChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.secondary
            )
        )
    }
}

@Composable
private fun ClassRoomsInfo(onClick: () -> Unit) {
    InfoSetting(
        onClick = onClick,
        infoName = "Aule"
    )
}


@Composable
private fun LibrariesInfo(onClick: () -> Unit) {
    InfoSetting(
        onClick = onClick,
        infoName = "Biblioteche"
    )
}

@Composable
private fun ContactsInfo(onClick: () -> Unit) {
    InfoSetting(
        onClick = onClick,
        infoName = "Contatti"
    )
}

@Composable
private fun InfoSetting(
    onClick: () -> Unit,
    infoName: String
) {
    Row(
        modifier = Modifier
            .padding(
                start = 30.dp,
                top = 10.dp,
                end = 20.dp,
                bottom = 10.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = infoName)
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_up_arrow),
                    contentDescription = "vedi ${infoName.lowercase()}",
                    modifier = Modifier
                        .rotate(90f)
                        .size(15.dp)
                )
            }
        }
    }
}
