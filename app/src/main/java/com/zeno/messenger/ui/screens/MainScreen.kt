package com.zeno.messenger.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.zeno.messenger.R

@Composable
fun MainScreen(navController: NavHostController) {
    val chats = emptyList<String>()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.screen_main_title)) })
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            Text(
                text = stringResource(id = R.string.screen_main_welcome),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            )

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                modifier = Modifier.fillMaxSize(),
            ) {
                if (chats.isEmpty()) {
                    item {
                        Text(text = stringResource(id = R.string.screen_main_empty))
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

