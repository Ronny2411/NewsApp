package com.example.newsapp.presentation.navgraph

sealed class Route(val route: String) {
    object OnBoardingScreen : Route(route = "onboardingscreen")
    object HomeScreen : Route(route = "homescreen")
    object SearchScreen : Route(route = "searchscreen")
    object DetailsScreen : Route(route = "detailsscreen")
    object BookmarkScreen : Route(route = "bookmarkscreen")
    object AppStartNavigation : Route(route = "appstartnavigation")
    object NewsNavigation : Route(route = "newsnavigation")
    object NewsNavigatorScreen : Route(route = "newsnavigatorscreen")
}