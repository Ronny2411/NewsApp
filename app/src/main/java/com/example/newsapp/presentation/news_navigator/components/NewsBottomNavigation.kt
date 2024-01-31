package com.example.newsapp.presentation.news_navigator.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.presentation.Dimens.ExtraSmallPadding2
import com.example.newsapp.presentation.Dimens.IconSize

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClicked:(Int)->Unit
) {
    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colorScheme.background,
        elevation = 10.dp
    ) {
        items.forEachIndexed{ index, item->
            BottomNavigationItem(
                selected = index == selected,
                onClick = { onItemClicked(index) },
                icon = { 
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(painter = painterResource(id = item.icon),
                            contentDescription = "",
                            modifier = Modifier.size(IconSize))
                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = colorResource(id = R.color.body)
            )
        }
    }
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)

@Preview
@Composable
fun NewsBottomNavigationPrev() {
    NewsBottomNavigation(items = listOf<BottomNavigationItem>(
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
        ),
        selected = 1,
        onItemClicked = {})
}