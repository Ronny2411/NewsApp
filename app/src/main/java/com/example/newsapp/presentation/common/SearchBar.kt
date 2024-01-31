package com.example.newsapp.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.presentation.Dimens.IconSize

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    readOnly: Boolean,
    onSearchClick:()->Unit,
    onTextChanged:(String)->Unit,
    onClick:(()->Unit) ?= null
) {
    val interactions = remember {
        MutableInteractionSource()
    }
    val focusManager = LocalFocusManager.current
    val isClicked = interactions.collectIsPressedAsState().value
    LaunchedEffect(key1 = isClicked){
        if (isClicked){
            onClick?.invoke()
        }
    }
    Box(modifier = modifier) {
        TextField(value = text,
            onValueChange = {onTextChanged(it)},
            modifier = Modifier
                .fillMaxWidth()
                .searchBarBorder(),
            readOnly = readOnly,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon),
                    modifier = Modifier.size(IconSize),
                    tint = colorResource(id = R.color.placeholder))
            },
            placeholder = { Text(text = "Search",
                                style = MaterialTheme.typography.bodySmall,
                                color = colorResource(id = R.color.placeholder))},
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.input_background),
                textColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {onSearchClick()
                focusManager.clearFocus()}
            ),
            textStyle = MaterialTheme.typography.bodySmall,
            interactionSource = interactions
        )
    }
}

fun Modifier.searchBarBorder() = composed {
    if (!isSystemInDarkTheme()){
        border(
            width = 1.dp,
            color = Color.Black,
            shape = MaterialTheme.shapes.medium
        )
    } else {
        this
    }
}