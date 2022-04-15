package controller

import models.Recipe

class RecipeAPI {
    var recipes = ArrayList<Recipe>()

    fun add(recipe: Recipe) {
        recipes.add(recipe)
    }

    fun list():String {
        return recipes.toString()
    }
}