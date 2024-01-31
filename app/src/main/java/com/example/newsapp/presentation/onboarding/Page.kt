package com.example.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.newsapp.R

data class Page(
    val title : String,
    val desc : String,
    @DrawableRes val image : Int
)

val pages = listOf(
    Page("Dummy Title for Onboarding Page 1",
        "Dummy Description for onboarding page Dummy Description for onboarding page",
        R.drawable.onboarding1),
    Page("Dummy Title for Onboarding Page 2",
        "Dummy Description for onboarding page Dummy Description for onboarding page",
        R.drawable.onboarding2),
    Page("Dummy Title for Onboarding Page 3",
        "Dummy Description for onboarding page Dummy Description for onboarding page",
        R.drawable.onboarding3)
)