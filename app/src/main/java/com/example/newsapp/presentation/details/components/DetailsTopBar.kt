package com.example.newsapp.presentation.details.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp.R

@Composable
fun DetailsTopBar(
    onBrowserClick:()->Unit,
    onShareClick:()->Unit,
    onBookmarkClick:()->Unit,
    onBackClick:()->Unit
) {
    TopAppBar(title = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth(),
        backgroundColor =  Color.Transparent,
        navigationIcon = {
            IconButton(onClick = { onBackClick()  }) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back_icon),
                    tint = colorResource(id = R.color.body)
                )
            }
        },
        actions = {
            IconButton(onClick = { onBookmarkClick() }) {
                Icon(painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = stringResource(R.string.bookmark_icon),
                    tint = colorResource(id = R.color.body)
                )
            }
            IconButton(onClick = { onShareClick() }) {
                Icon(imageVector = Icons.Default.Share,
                    contentDescription = stringResource(R.string.share_icon),
                    tint = colorResource(id = R.color.body)
                )
            }
            IconButton(onClick = { onBrowserClick() }) {
                Icon(painter = painterResource(id = R.drawable.ic_network),
                    contentDescription = stringResource(R.string.browse_icon),
                    tint = colorResource(id = R.color.body)
                )
            }
        })
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailsTopBarPrev() {
    DetailsTopBar(
        onBrowserClick = { /*TODO*/ },
        onShareClick = { /*TODO*/ },
        onBookmarkClick = { /*TODO*/ }) {

    }
}