package com.example.newsapp.presentation.search

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.Dimens
import com.example.newsapp.presentation.Dimens.MediumPadding1
import com.example.newsapp.presentation.Dimens.MediumPadding2
import com.example.newsapp.presentation.common.ArticleList
import com.example.newsapp.presentation.common.EmptyScreen
import com.example.newsapp.presentation.common.PlaceHolderScreen
import com.example.newsapp.presentation.common.SearchBar
import com.example.newsapp.presentation.navgraph.Route
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SearchScreen(
    searchState: SearchState,
    event: (SearchEvent)-> Unit,
    navigateToDetails: (Article)-> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
        .padding(
            top = MediumPadding1
        )
        .statusBarsPadding()) {
        SearchBar(text = searchState.searchQuery,
            readOnly = false,
            onSearchClick = {
                event(SearchEvent.SearchNews)
            },
            onTextChanged = {
                event(SearchEvent.UpdateSearchQuery(it))
            },
            modifier = Modifier.padding(horizontal = MediumPadding1))
        Spacer(modifier = Modifier.height(MediumPadding1))
        if (searchState.searchQuery == ""){
            PlaceHolderScreen(text = "Find Your News!")
            searchState.article = emptyFlow()
        } else {
            searchState.article?.let {
                val articles = it.collectAsLazyPagingItems()
                ArticleList(articles = articles, onClick = { navigateToDetails(it) })
            }
        }
    }
}