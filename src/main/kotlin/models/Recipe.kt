package models

/**
 * [recipeTitle] is the name of the recipe
 * [recipeInstructions] are considered to put in with copy/paste. Be aware that a newLine ends all Instructions
 * [recipeIngredients] are all ingredients for this recipe
 *
 * @author niklas7U
 * @since V 0
 * @constructor Creates a new instance of Recipe
 * */
data class Recipe<T>(var recipeTitle: String,
                     var recipeInstructions: String,
                     var recipeIngredients: ArrayList<Ingredient>
)