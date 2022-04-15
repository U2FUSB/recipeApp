package models

data class Ingredient(
    var ingredientName: String,
    var ingredientAmount: Int,
    var ingredientUnit: String = "g"
)
