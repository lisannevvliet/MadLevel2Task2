package com.example.madlevel2task2

data class Statement(
        var statement: String,
        var answer: Boolean
) {
    companion object {
        val STATEMENTS = arrayOf(
                "A 'val' and 'var' are the same.",
                "Mobile Application Development grants 12 ECTS.",
                "A Unit in Kotlin corresponds to a void in Java.",
                "In Kotlin 'when' replaces the 'switch' operator in Java.",
                "The HvA teaches object-oriented programming.",
                "Kotlin is more similar to Python than to Java."
        )

        val ANSWERS = arrayOf(
                false,
                false,
                true,
                true,
                true,
                false
        )
    }
}