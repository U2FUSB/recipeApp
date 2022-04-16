package presenter

import models.Recipe

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
    fun list():String {
        return recipes.toString()
    }
}