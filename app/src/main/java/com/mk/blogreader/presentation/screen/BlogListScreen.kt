package com.mk.blogreader.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.mk.blogreader.data.model.BlogItem
import com.mk.blogreader.presentation.viewmodel.BlogViewmodel
import com.mk.blogreader.util.TransparentSystemBars

@Composable
fun BlogListScreen(
    viewModel: BlogViewmodel,
    onClick: (BlogItem) -> Unit
) {

    val blogs = viewModel.blogsPost.collectAsLazyPagingItems()

    TransparentSystemBars()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        Text(
            text = "Blog Posts",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )

        LazyColumn(
            modifier = Modifier
        ) {

            items(blogs.itemCount) { index ->
                val blog = blogs[index]
                blog?.let {
                    BlogItemCard(
                        blog = it,
                        onClick = { onClick(it) }
                    )
                }
            }

            blogs.apply {
                when {
                    loadState.refresh is LoadState.Loading ||
                            loadState.append is LoadState.Loading ->
                        item {
                            androidx.compose.foundation.layout.Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = androidx.compose.ui.Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    color = androidx.compose.ui.graphics.Color.Blue
                                )
                            }
                        }
                }
            }

        }

    }


}

@Composable
fun BlogItemCard(
    blog: BlogItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = blog.title.rendered, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = blog.date, style = MaterialTheme.typography.bodySmall)
        }
    }
}
