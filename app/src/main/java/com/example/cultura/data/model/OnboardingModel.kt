package com.example.cultura.data.model

import androidx.annotation.DrawableRes
import com.example.cultura.R

sealed class OnboardingModel(
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
) {

    data object FirstPage : OnboardingModel(
        image = R.drawable.step1,
        title = "Welcome to Your Cultural Universe",
        description = "Discover movies, books, and more – all tailored to your unique tastes and preferences."
    )

    data object SecondPage : OnboardingModel(
        image = R.drawable.step2,
        title = "Recommendations Made Just for You",
        description = "We use your preferences to suggest content you'll actually enjoy."
    )

    data object ThirdPages : OnboardingModel(
        image = R.drawable.step3,
        title = "Ready to Explore?",
        description = "Start browsing your top cultural picks in just a few taps."
    )


}