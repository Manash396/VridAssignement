package com.mk.blogreader.util

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun TransparentSystemBars() {
    val view = LocalView.current
    val window =  (view.context as Activity).window

    SideEffect {
        WindowCompat.setDecorFitsSystemWindows(window,false)
        val controller = WindowInsetsControllerCompat(window, view)

        controller.isAppearanceLightStatusBars = true
        controller.isAppearanceLightNavigationBars = true
    }
}