package com.zeno.messenger.web

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

private enum class Route {
    EnterPhone,
    VerifyCode,
    Main,
}

private val LightColorScheme = lightColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFF6750A4),
    secondary = androidx.compose.ui.graphics.Color(0xFF625B71),
    tertiary = androidx.compose.ui.graphics.Color(0xFF7D5260),
    background = androidx.compose.ui.graphics.Color(0xFFFFFBFE),
    surface = androidx.compose.ui.graphics.Color(0xFFFFFBFE),
    onPrimary = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
    onSecondary = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
    onTertiary = androidx.compose.ui.graphics.Color(0xFFFFFFFF),
    onBackground = androidx.compose.ui.graphics.Color(0xFF1C1B1F),
    onSurface = androidx.compose.ui.graphics.Color(0xFF1C1B1F),
)

private val DarkColorScheme = darkColorScheme(
    primary = androidx.compose.ui.graphics.Color(0xFFD0BCFF),
    secondary = androidx.compose.ui.graphics.Color(0xFFCCC2DC),
    tertiary = androidx.compose.ui.graphics.Color(0xFFEFB8C8),
    background = androidx.compose.ui.graphics.Color(0xFF1C1B1F),
    surface = androidx.compose.ui.graphics.Color(0xFF1C1B1F),
    onPrimary = androidx.compose.ui.graphics.Color(0xFF381E72),
    onSecondary = androidx.compose.ui.graphics.Color(0xFF332D41),
    onTertiary = androidx.compose.ui.graphics.Color(0xFF492532),
    onBackground = androidx.compose.ui.graphics.Color(0xFFE6E1E5),
    onSurface = androidx.compose.ui.graphics.Color(0xFFE6E1E5),
)

@Composable
private fun ZenoTheme(content: @Composable () -> Unit) {
    val colorScheme = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography(),
        content = content,
    )
}

@Composable
fun ZenoWebApp() {
    ZenoTheme {
        ZenoAppContent()
    }
}

@Composable
private fun ZenoAppContent() {
    var route by remember { mutableStateOf(Route.EnterPhone) }
    when (route) {
        Route.EnterPhone -> EnterPhoneScreen(
            onNext = { route = Route.VerifyCode },
        )

        Route.VerifyCode -> VerifyCodeScreen(
            onBackToMain = { route = Route.Main },
        )

        Route.Main -> MainScreen()
    }
}

@Composable
private fun EnterPhoneScreen(
    onNext: () -> Unit,
) {
    var phoneNumber by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Вход") })
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { input ->
                    val digits = input.filter { it.isDigit() }
                    phoneNumber = digits.take(20)
                },
                label = { Text(text = "Номер телефона") },
                placeholder = { Text(text = "+7 999 123-45-67") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done,
                ),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onNext,
                enabled = phoneNumber.isNotBlank(),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Далее")
            }
        }
    }
}

@Composable
private fun VerifyCodeScreen(
    onBackToMain: () -> Unit,
) {
    var code by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Подтверждение") })
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "Введите 6-значный код из SMS")

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = code,
                onValueChange = { input ->
                    val digits = input.filter { it.isDigit() }.take(6)
                    code = digits
                },
                label = { Text(text = "Код подтверждения") },
                placeholder = { Text(text = "123456") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                ),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onBackToMain,
                enabled = code.length == 6,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Подтвердить")
            }
        }
    }
}

@Composable
private fun MainScreen() {
    val chats = emptyList<String>()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Zeno") })
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            Text(
                text = "Добро пожаловать!",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            )

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                modifier = Modifier.fillMaxSize(),
            ) {
                if (chats.isEmpty()) {
                    items(listOf("empty")) {
                        Text(text = "Пока нет чатов")
                    }
                } else {
                    items(chats) { chat ->
                        Text(text = chat)
                    }
                }
            }
        }
    }
}

