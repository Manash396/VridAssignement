package com.mk.blogreader.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mk.blogreader.presentation.screen.BlogListScreen
import com.mk.blogreader.presentation.screen.BlogWebScreen
import com.mk.blogreader.presentation.viewmodel.BlogViewmodel

@Composable
fun AppNavHost(
    viewModel: BlogViewmodel
){
    val navController  = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.BLOG_LIST
    ){
        composable(Screens.BLOG_LIST) {
            BlogListScreen(viewModel) {blog ->
                viewModel.selectedBlog(blog)
                navController.navigate(Screens.BLOG_WEBVIEW)
            }
        }
        composable(Screens.BLOG_WEBVIEW) {
            BlogWebScreen(viewModel){
                navController.popBackStack()
            }
        }
    }

}