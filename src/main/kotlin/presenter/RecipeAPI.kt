package presenter

import models.Recipe
import utils.IndexChecker

/**
 * Manages the [recipes] ArrayList
 *
 * @author niklas7U
 * @since V 0
 * @constructor Creates a new instance of RecipeAPI
 * */
class RecipeAPI {
    private var recipes = ArrayList<Recipe>()

    /**
     * adds a given Recipe to the [recipes] ArrayList
     *
     * @param [recipe]: Recipe
     * @since V 0
     * */
    fun add(recipe: Recipe) {
        recipes.add(recipe)
    }

    /**
     * lists all recipes in [recipes] ArrayList
     *
     * @since V 0
     * */
    fun list(): String {
        return recipes.toString()
    }

    /**
     * updates a recipe, using its index and user input
     *
     * @param [indexToUpdate] index of recipe, wished to update
     * @param [recipe] the new (updated) recipe
     * @return [Boolean] representing success or failure
     * @since V 0
     * */
    fun update(indexToUpdate: Int, recipe: Recipe): Boolean {
        val foundRecipe = findRecipe(indexToUpdate)
        if (foundRecipe != null) {
            foundRecipe.recipeTitle = recipe.recipeTitle
            foundRecipe.recipeInstructions = recipe.recipeInstructions
            foundRecipe.recipeIngredients = recipe.recipeIngredients
            return true
        }
        return false
    }

    private fun findRecipe(index: Int): Recipe? =
        if (IndexChecker.isValidIndex(index, recipes)) {
            recipes[index]
        } else null

    /**
     * deletes a recipe, using it's index
     * @param indexToDelete index of recipe, wished to be deleted
     * @return the recipe of type [Recipe], or [null] in case of failure
     * @since V 0
     * */
    fun delete(indexToDelete: Int): Recipe? =
        if (IndexChecker.isValidIndex(indexToDelete, recipes)) {
            recipes.removeAt(indexToDelete)
        } else null
}