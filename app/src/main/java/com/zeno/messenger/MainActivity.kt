package com.zeno.messenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zeno.messenger.ui.navigation.NavGraph
import com.zeno.messenger.ui.theme.ZenoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZenoTheme {
                NavGraph()
            }
        }
    }
}

