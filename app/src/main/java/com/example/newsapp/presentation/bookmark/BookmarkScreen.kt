package com.example.newsapp.presentation.bookmark

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
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.Dimens
import com.example.newsapp.presentation.Dimens.MediumPadding1
import com.example.newsapp.presentation.Dimens.MediumPadding2
import com.example.newsapp.presentation.common.ArticleList
import com.example.newsapp.presentation.common.PlaceHolderScreen
import com.example.newsapp.presentation.navgraph.Route
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun BookMarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article)->Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
        .statusBarsPadding()
        .padding(
            top = MediumPadding1,
            start = MediumPadding2,
            end = MediumPadding2
        )) {
        Text(text = stringResource(R.string.bookmark),
            style = MaterialTheme.typography.displayMedium
                .copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title))
        Spacer(modifier = Modifier.height(MediumPadding1))
        if (state.articles.isEmpty()) {
            PlaceHolderScreen(text = "You have not saved news so far!")
        } else {
            ArticleList(articles = state.articles,
                onClick = { navigateToDetails(it) })
        }
    }
}