package com.example.mitalk.sample.mvi

data class SampleState(
    val clickCount: Int = 0,

    val returnCount: String = "",
)

sealed class SampleEffect {

    object CannotCountClickCount : SampleEffect()

    object TooManyClickCount : SampleEffect()

    object UnknownException : SampleEffect()
}