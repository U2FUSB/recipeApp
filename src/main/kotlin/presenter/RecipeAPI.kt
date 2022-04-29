package presenter

import models.Recipe
import persistence.Serializer
import utils.IndexChecker
import kotlin.jvm.Throws

/**
 * Manages the [recipes] ArrayList
 *
 * @author niklas7U
 * @since V 0
 * @constructor Creates a new instance of RecipeAPI
 * */
class RecipeAPI (serializerType: Serializer) {
    private var serializer: Serializer = serializerType
    private var recipes = ArrayList<Recipe<Any?>>()

    /**
     * adds a given Recipe to the [recipes] ArrayList
     *
     * @param [recipe]: Recipe
     * @since V 0
     * */
    fun add(recipe: Recipe<Any?>?) {
        recipes.add(recipe!!)
    }

    /**
     * lists all recipes in [recipes] ArrayList
     *
     * @param [indexOfRecipe]
     * @since V 0
     * */
    fun list(indexOfRecipe: Int? = null): String =
            if (recipes.isEmpty()) {
                "No recipes stored"
            } else {
                if (indexOfRecipe == null) {
                    formatRecipes(recipes)
                } else {
                    formatRecipes(arrayListOf(recipes[indexOfRecipe]))
                }
            }

    /**
     * list the names from all recipes in [recipes] ArrayList
     *
     * @return [String] containing all [recipe.Title]
     * @since V 2
     * */
    fun listAllNames(foundRecipes: ArrayList<Recipe<Any?>>? = null): String {
        val recipesToList: ArrayList<Recipe<Any?>> = foundRecipes ?: recipes
        return recipesToList.joinToString { "\nNumber ${recipes.indexOf(it)} : ${it.recipeTitle}" }
    }

    /**
     * updates a recipe, using its index and user input
     *
     * @param [indexToUpdate] index of recipe, wished to update
     * @param [recipe] the new (updated) recipe
     * @return [Boolean] representing success or failure
     * @since V 0
     * */
    fun update(indexToUpdate: Int, recipe: Recipe<Any?>): Boolean {
        val foundRecipe = findRecipe(indexToUpdate)
        if (foundRecipe != null) {
            foundRecipe.recipeTitle = recipe.recipeTitle
            foundRecipe.recipeInstructions = recipe.recipeInstructions
            foundRecipe.recipeIngredients = recipe.recipeIngredients
            return true
        }
        return false
    }

    /**
     * finds a recipe by its index
     *
     * @param [index]
     * @return found [recipe] or null
     * @since V 2
     * */
    fun findRecipe(index: Int): Recipe<Any?>? =
        if (IndexChecker.isValidIndex(index, recipes)) {
            recipes[index]
        } else null

    /**
     * deletes a recipe, using it's index
     * @param indexToDelete index of recipe, wished to be deleted
     * @return the recipe of type [Recipe], or [null] in case of failure
     * @since V 0
     * */
    fun delete(indexToDelete: Int): Recipe<Any?>? =
        if (IndexChecker.isValidIndex(indexToDelete, recipes)) {
            recipes.removeAt(indexToDelete)
        } else null

    /**
     * counts Recipes in [recipes] Arraylist
     *
     * @return [recipes.size]
     * @see [size]
     * @since V 0*/
    fun numberOfRecipes(): Int =
        recipes.size

    /**
     * returns the whole recipe arraylist
     *
     * @return [recipes]
     * @since V 1*/
    fun get(): ArrayList<Recipe<Any?>> {
        return recipes
    }

    private fun formatRecipes(recipesToFormat : List<Recipe<Any?>>) : String =
        recipesToFormat.joinToString{ """
            |
            | 
            |******************************************
            |Number ${recipes.indexOf(it)} : ${it.recipeTitle}                                         
            |******************************************
            |                                                                                   
            |INSTRUCTIONS:
            |_____________
            |                                                                                   
            |${it.recipeInstructions}                                                          
            |                                                                                   
            |                                                                                   
            |INGREDIENTS:
            |____________                                                                                                                                                         
            |${it
            .recipeIngredients.map{"\n${it.ingredientName } ${it.ingredientAmount} ${it.ingredientUnit}"}
            .toString()
            .removePrefix("[")
            .removeSuffix("]")
            }                                                           
            |__________________________________________________________________________________
            |__________________________________________________________________________________
            |""".trimMargin() }

    /**
     * loads existing arraylist from persistent file (xml or json)
     *
     * @see [serializer.read]
     * @since V 1*/
    @Throws(Exception::class)
    fun load() {
        recipes = serializer.read() as ArrayList<Recipe<Any?>>
    }

    /**
     * saves actual arraylist in persistent file (xml or json)
     *
     * @see [serializer.write]
     * @since V 1*/
    @Throws(Exception::class)
    fun store() {
        serializer.write(recipes)
    }

    /**
     * Searches existing recipes in [recipes] for provided substring [ingredient] and returns them as List of Recipes of Any
     *
     * @param [ingredient] A string provided with user input. Might be an ingredient, or just a substring of one
     * @return List<Recipe<Any?>> all recipes containing the substring "[ingredient]" in - at least - one of their ingredient names
     * @since V 2
     *  */
    fun findIngredient(ingredient: String): List<Recipe<Any?>> {
        return recipes.filter { it.recipeIngredients.any { it.ingredientName.contains(ingredient) } }
    }
}