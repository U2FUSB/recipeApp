package views

import models.Ingredient
import presenter.IngredientAPI
import utils.IndexChecker
import utils.ScannerInput
import kotlin.collections.ArrayList

/**
 * Submenu which manages applying Ingredients to a recipe
 *
 * @author niklas7U
 * @since V 0
 * @constructor Creates a new instance of IngredientView
 * */
class IngredientView {

    private val ingredientAPI = IngredientAPI()
    private var ingredientsAccepted = false


    /**
     * Shows the Ingredients Menu
     * Asks the User to enter a Number
     * @since V 0
     * @return Number entered by User
     * @see [ScannerInput.readNextInt]
     * */
    private fun showMenu() : Int {
        return ScannerInput.readNextInt("""
        > 
        > 
        > ****************************
        > + RECIPE MANAGEMENT APP    +
        > ****************************
        > + INGREDIENT MENU          +
        > +   1) Add an ingredient   +
        > +   2) List ingredients    +
        > +   3) Update an ingredient+
        > +   4) Delete an ingredient+
        > ****************************
        > +   0) Accept ingredients  +
        > ****************************
        > ==>> 
    """.trimMargin(">"))
    }

    /**
     * runs submenu to manage ingredients of a chosen recipe
     * Depending on choice activates different functions allowing:
     * - adding 1
     * - listing 2
     * - updating 3
     * - deleting 4
     * Ingredients
     *
     * Entering 0 accepts chosen ingredients
     * @since V 0
     * @param [menu] type Int: Choice from [showMenu]
     * @return [IngredientAPI.get] This returns the current arraylist of ingredients
     * @see [showMenu]
     *
     * @see [addIngredient]
     * @see [listIngredients]
     * @see [updateIngredient]
     * @see [deleteIngredient]
     * @see [acceptIngredients]
     * */
    fun runMenu(existingIngredients: ArrayList<Ingredient>? = null):ArrayList<Ingredient> {
        ingredientsAccepted = false
        if (existingIngredients != null) {
            ingredientAPI.addAll(existingIngredients)
        }
        do {
            when (val menu = showMenu()) {
                1 -> addIngredient()
                2 -> listIngredients()
                3 -> updateIngredient()
                4 -> deleteIngredient()
                0 -> acceptIngredients()
                else -> println("Option $menu is invalid. Try another one")
            }
        } while (!ingredientsAccepted)
        return ingredientAPI.get()
    }

    /**
     * deletes an ingredient, using it's index
     *
     * @since V 0*/
    private fun deleteIngredient() {
        if (ingredientAPI.numberOfIngredients() > 0) {
            listIngredients()
            val indexToDelete = ScannerInput.readNextInt("Enter the index of the ingredient to delete: ")
            if (IndexChecker.isValidIndex(indexToDelete, ingredientAPI.get())) {
                val ingredientToDelete = ingredientAPI.delete(indexToDelete)
                if (ingredientToDelete != null) {
                    println("Delete Successful! Deleted Ingredient: ${ingredientToDelete.ingredientName}")
                } else {
                    println("Delete NOT Successful")
                }
            } else {
                println("Index does not exist. Delete Failed")
            }
        } else {
            println("no ingredients stored yet")
        }
    }

    /**
     * updates an ingredient, using its index and user input
     *
     * @since V 0*/
    private fun updateIngredient() {
        listIngredients()
        val indexToUpdate = ScannerInput.readNextInt("Enter the index of the Ingredient to update:\n")
        if (IndexChecker.isValidIndex(indexToUpdate, ingredientAPI.get())) {
            val name = ScannerInput.readNextLine("Please enter name for Ingredient:\n")
            val amount = ScannerInput.readNextInt("Please enter amount for Ingredient:\n")
            val unit = ScannerInput.readNextLine("Please enter unit for amount:\n")
            if (ingredientAPI.update(indexToUpdate, Ingredient(name, amount, unit))) {
                println("Update Successful")
            } else {
                println("Update Failed")
            }
        } else {
            println("Index does not exist. Update Failed")
        }
    }

    /**
     * lists all ingredients
     *
     * @since V 0*/
    private fun listIngredients() {
        if (ingredientAPI.numberOfIngredients() > 0) {
            println(ingredientAPI.list())
        } else {
            println("no ingredients stored yet")
        }
    }

    /**
     * adds a given ingredient to the [ingredients] ArrayList
     *
     * @since V 0*/
    private fun addIngredient() {
        val name = ScannerInput.readNextLine("Please enter name for Ingredient:\n")
        val amount = ScannerInput.readNextInt("Please enter amount for Ingredient:\n")
        val unit = ScannerInput.readNextLine("Please enter unit for amount:\n")
        ingredientAPI.add(Ingredient(name, amount, unit))
    }

    /**
     * accepts chosen ingredients
     *
     * @since V 0*/
    private fun acceptIngredients() {
        ingredientsAccepted = true
    }
}