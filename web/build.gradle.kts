@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

plugins {
    kotlin("multiplatform") version "2.2.10"
    id("org.jetbrains.compose") version "1.8.2"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.10"
}

kotlin {
    wasmJs {
        // Важно: index.html из шаблона ожидает файл `composeApp.js`.
        outputModuleName = "composeApp"
        browser()
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
            }
        }
        val wasmJsMain by getting {
            dependencies {
                // Material 3 для Wasm/Compose Multiplatform.
                implementation("org.jetbrains.compose.material3:material3-wasm-js:1.8.2")
            }
        }
    }
}

