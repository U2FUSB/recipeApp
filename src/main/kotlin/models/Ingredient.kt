package models

data class Ingredient(
    var name: String,
    var amount: Double,
    var unit: String = "g"
)
