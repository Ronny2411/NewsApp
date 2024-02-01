package com.example.newsapp.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.bookmark.BookMarkScreen
import com.example.newsapp.presentation.bookmark.BookmarkViewModel
import com.example.newsapp.presentation.details.DetailsEvent
import com.example.newsapp.presentation.details.DetailsScreen
import com.example.newsapp.presentation.details.DetailsViewModel
import com.example.newsapp.presentation.home.HomeScreen
import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.presentation.navgraph.Route
import com.example.newsapp.presentation.news_navigator.components.BottomNavigationItem
import com.example.newsapp.presentation.news_navigator.components.NewsBottomNavigation
import com.example.newsapp.presentation.search.SearchScreen
import com.example.newsapp.presentation.search.SearchViewModel

@Composable
fun NewsNavigator() {
    val bottomNavigationItem = remember {
        listOf<BottomNavigationItem>(
            BottomNavigationItem(
                icon = R.drawable.ic_home,
                text = "Home"
            ),
            BottomNavigationItem(
                icon = R.drawable.ic_search,
                text = "Search"
            ),
            BottomNavigationItem(
                icon = R.drawable.ic_bookmark,
                text = "Bookmark"
            )
        )
    }

    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by remember {
        mutableStateOf(0)
    }
    selectedItem = when(backStackState?.destination?.route){
        Route.HomeScreen.route-> 0
        Route.SearchScreen.route-> 1
        Route.BookmarkScreen.route-> 2
        else-> 0
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
        backStackState?.destination?.route == Route.SearchScreen.route ||
        backStackState?.destination?.route == Route.BookmarkScreen.route
    }
    Scaffold(scaffoldState = scaffoldState
        ,modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        bottomBar = {
            if (isBottomBarVisible){
                NewsBottomNavigation(items = bottomNavigationItem,
                    selected = selectedItem,
                    onItemClicked = { index ->
                        when (index) {
                            0 -> navigateToTab(navController, Route.HomeScreen.route)
                            1 -> navigateToTab(navController, Route.SearchScreen.route)
                            2 -> navigateToTab(navController, Route.BookmarkScreen.route)
                        }
                    }
                )
            }
        }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)){
            composable(Route.HomeScreen.route){
                val viewModel: HomeViewModel = hiltViewModel()
                val article = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(article = article,
                    navigateToSearch = { navigateToTab(navController,Route.SearchScreen.route) },
                    navigateToDetails = { navigateToDetails(navController,it) })
            }
            composable(Route.SearchScreen.route){
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(searchState = viewModel.state.value,
                    event = viewModel::onEvent,
                    navigateToDetails = { navigateToDetails(navController,it) })
            }
            composable(Route.DetailsScreen.route){
                val viewModel: DetailsViewModel = hiltViewModel()
                if (viewModel.sideEffect!=null){
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")?.let {article ->
                    DetailsScreen(article = article,
                        event = viewModel::onEvent,
                        navigateUp = {navController.navigateUp()
                            navController.previousBackStackEntry?.savedStateHandle?.remove<Article?>("article")})
                }
            }
            composable(Route.BookmarkScreen.route){
                val viewModel: BookmarkViewModel = hiltViewModel()
                BookMarkScreen(state = viewModel.state.value,
                    navigateToDetails = { navigateToDetails(navController,it) })
            }
        }
    }
}

private fun navigateToTab(navController: NavController, route: String){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let { homeScreen->
            popUpTo(homeScreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController, article: Article){
    navController.currentBackStackEntry?.savedStateHandle?.set("article",article)
    navController.navigate(Route.DetailsScreen.route)
}