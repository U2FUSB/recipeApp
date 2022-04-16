package views

import models.Ingredient
import presenter.IngredientAPI
import utils.ScannerInput
import java.util.ArrayList

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
        > ----------------------------
        > | RECIPE MANAGEMENT APP    |
        > ----------------------------
        > | INGREDIENT MENU          |
        > |   1) Add an ingredient   |
        > |   2) List ingredients    |
        > |   3) Update an ingredient|
        > |   4) Delete an ingredient|
        > ----------------------------
        > |   0) Accept ingredients  |
        > ----------------------------
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
    fun runMenu():ArrayList<Ingredient> {
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
        // TODO implement deleting ingredients
        ingredientAPI.delete()
    }

    /**
     * updates an ingredient, using its index and user input
     *
     * @since V 0*/
    private fun updateIngredient() {
        val indexToUpdate = ScannerInput.readNextInt("Enter the index of the Ingredient to update:\n")
        val name = ScannerInput.readNextLine("Please enter name for Ingredient:\n")
        val amount = ScannerInput.readNextInt("Please enter amount for Ingredient:\n")
        val unit = ScannerInput.readNextLine("Please enter unit for amount:\n")
        if (ingredientAPI.update(indexToUpdate, Ingredient(name, amount, unit))) {
                println("Update Successful")
        } else {
                println("Update Failed")
        }
    }

    /**
     * list all ingredients
     *
     * @since V 0*/
    private fun listIngredients() {
        println(ingredientAPI.get())
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