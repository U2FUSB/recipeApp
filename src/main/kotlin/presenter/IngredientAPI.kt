package presenter

import models.Ingredient
import utils.IndexChecker

class IngredientAPI {
    private var ingredients = ArrayList<Ingredient>()

    fun get(): ArrayList<Ingredient> {
        return ingredients
    }

    fun add(ingredient: Ingredient) {
        ingredients.add(ingredient)
    }

    fun update(indexToUpdate: Int, ingredient: Ingredient): Boolean {
        val foundIngredient = findIngredient(indexToUpdate)
        if (foundIngredient != null) {
            foundIngredient.ingredientName = ingredient.ingredientName
            foundIngredient.ingredientAmount = ingredient.ingredientAmount
            foundIngredient.ingredientUnit = ingredient.ingredientUnit
            return true
        }
        return false
    }


    fun delete() {
        println("not implemented yet")
    }

    private fun findIngredient(index: Int): Ingredient? =
        if (IndexChecker.isValidIndex(index, ingredients)) {
            ingredients[index]
        } else null
}