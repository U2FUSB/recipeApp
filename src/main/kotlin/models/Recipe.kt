package models

data class Recipe(var recipeTitle: String,
             var recipeInstructions: String,
             var recipeIngredients: ArrayList<Ingredient>
)