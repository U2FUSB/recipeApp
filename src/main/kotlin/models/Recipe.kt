package models

data class Recipe(var recipeTitle: String,
             var recipeInstructions: String,
             var recipeIngredients: ArrayList<Ingredient> = arrayListOf(
                 Ingredient("butter", 20,"g"),
                 Ingredient("flour", 50,"g"),
                 Ingredient("sugar",10 ,"g")
             )
)