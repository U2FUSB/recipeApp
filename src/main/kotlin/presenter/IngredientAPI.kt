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
     * Lists all Ingredients
     *
     * @return [ingredients.toString]
     * @see toString
     * @since V 0
     * */
    fun list(): String =
        if (ingredients.isEmpty()) {
            "No notes stored"
        } else {
            formatIngredients(ingredients)
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
    fun delete(indexToDelete: Int): Ingredient? =
        if (IndexChecker.isValidIndex(indexToDelete, ingredients)) {
            ingredients.removeAt(indexToDelete)
        } else null

    private fun findIngredient(index: Int): Ingredient? =
        if (IndexChecker.isValidIndex(index, ingredients)) {
            ingredients[index]
        } else null

    /**
     * adds all existing Ingredients to the Arraylist of Ingredients at once
     *
     * @param existingIngredients
     * @since V 0*/
    fun addAll(existingIngredients: java.util.ArrayList<Ingredient>) {
        ingredients.addAll(existingIngredients)
    }

    /**
     * counts Ingredients in [ingredients] Arraylist
     *
     * @return [ingredients.size]
     * @see [size]
     * @since V 0*/
    fun numberOfIngredients(): Int =
        ingredients.size

    private fun formatIngredients(ingredientsToFormat : List<Ingredient>) : String =
        ingredientsToFormat.joinToString{ "\n${ingredients.indexOf(it)} : " +
                "${it.ingredientName} " +
                "${it.ingredientAmount} " +
                it.ingredientUnit
        }
}