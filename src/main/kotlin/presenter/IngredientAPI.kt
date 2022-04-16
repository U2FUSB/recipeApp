package presenter

import models.Ingredient
import utils.IndexChecker

/**
 * @author niklas7U
 * @since V 0
 * @constructor Creates a new instance of IngredientAPI
 * */
class IngredientAPI {
    private var ingredients = ArrayList<Ingredient>()

    /**
     * Returns the arraylist of ingredients
     *
     * @return [ingredients]
     * @since V 0
     * */
    fun get(): ArrayList<Ingredient> {
        return ingredients
    }

    /**
     * adds a given Ingredient to the [ingredients] ArrayList
     *
     * @param [ingredient]: Ingredient
     * @since V 0
     * */
    fun add(ingredient: Ingredient) {
        ingredients.add(ingredient)
    }

    /**
     * updates a given ingredient by its index
     *
     * @param [indexToUpdate]: Int
     * @param [ingredient]: Ingredient
     * @return Boolean value representing success or failure
     * @since V 0
     * */
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

    /**
     * deletes a given ingredient
     *
     * @since V 0*/
    fun delete() {
        println("not implemented yet")
    }

    private fun findIngredient(index: Int): Ingredient? =
        if (IndexChecker.isValidIndex(index, ingredients)) {
            ingredients[index]
        } else null
}