package com.example.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.newsapp.R

data class Page(
    val title : String,
    val desc : String,
    @DrawableRes val image : Int
)

val pages = listOf(
    Page("Search worldwide news",
        "Locate articles and breaking news headlines from news sources and blogs across the web",
        R.drawable.onboarding1),
    Page("First voice, last word",
        "India's leading English news website that reaches you directly on your device of choice",
        R.drawable.onboarding2),
    Page("Showcase",
        "Experience our diverse range of multimedia stories and deep coverage across topics from " +
                "current affairs to arts and beyond",
        R.drawable.onboarding3)
)