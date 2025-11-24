package com.mk.blogreader.presentation.screen


import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.mk.blogreader.presentation.viewmodel.BlogViewmodel
import com.mk.blogreader.util.TransparentSystemBars

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogWebScreen(
    viewModel: BlogViewmodel,
    onBackClick: () -> Unit
) {
    val blog = viewModel.selectedBlog.value

    TransparentSystemBars()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        TopAppBar(
            title = { Text(text = "WebView") },
            navigationIcon = {
                IconButton(
                    onClick = {onBackClick()}
                ) {
                    Icon(imageVector = Icons.Filled.ArrowUpward,
                        contentDescription = "back",
                        modifier = Modifier.rotate(-90f)
                    )
                }
            }
        )

        blog?.let {
            val url = blog.link
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        loadUrl(url)
                    }
                }
            )
        }

    }


}