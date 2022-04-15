package views

import models.Ingredient
import presenter.IngredientAPI
import utils.ScannerInput
import java.util.ArrayList

class IngredientView {

    private val ingredientAPI = IngredientAPI()
    private var ingredientsAccepted = false
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

    private fun deleteIngredient() {
        // TODO implement deleting ingredients
        ingredientAPI.delete()
    }

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

    private fun listIngredients() {
        // TODO listing ingredients does not work jet
        ingredientAPI.get()
    }

    private fun addIngredient() {
        val name = ScannerInput.readNextLine("Please enter name for Ingredient:\n")
        val amount = ScannerInput.readNextInt("Please enter amount for Ingredient:\n")
        val unit = ScannerInput.readNextLine("Please enter unit for amount:\n")
        ingredientAPI.add(Ingredient(name, amount, unit))
    }

    private fun acceptIngredients() {
        ingredientsAccepted = true
    }
}