package views

import models.Recipe
import presenter.RecipeAPI
import utils.ScannerInput

class RecipeView {
    private val recipeAPI = RecipeAPI()
    private val ingredientView = IngredientView()

    /**Shows the menu of recipeApp.
     * Basis for UI
     *@author Niklas Fehringer*/
    private fun showMenu() : Int {
        return ScannerInput.readNextInt("""
        > 
        > 
        > ---------------------------
        > | RECIPE MANAGEMENT APP   |
        > ---------------------------
        > | RECIPE MENU             |
        > |   1) Add a recipe       |
        > |   2) List recipes       |
        > |   3) Update a recipe    |
        > |   4) Delete a recipe    |
        > ---------------------------
        > |   0) Exit               |
        > ---------------------------
        > ==>> 
    """.trimMargin(">"))
    }
    fun runMenu() {
        do {
            when (val menu = showMenu()) {
                1 -> addRecipe()
                2 -> listRecipes()
                3 -> updateRecipe()
                4 -> deleteRecipe()
                0 -> exitProgram()
                else -> println("Option $menu is invalid. Try another one")
            }
        } while (true)
    }

    private fun exitProgram() {
        println("bye, see you :)")
        kotlin.system.exitProcess(0)
    }

    private fun deleteRecipe() {
        //TODO("deleteRecipe Not yet implemented")
        println("deleteRecipe")
    }

    private fun updateRecipe() {
        //TODO("updateRecipe Not yet implemented")
        println("updateRecipe")
    }

    private fun listRecipes() {
        //TODO listRecipes not beautiful enough yet
        println(recipeAPI.list())
    }

    private fun addRecipe() {
        val title = ScannerInput.readNextLine("Please enter title of recipe: \n")
        val instructions = ScannerInput.readNextLine(("Please enter/copy instructions of recipe in here: \n"))
        val ingredients = ingredientView.runMenu()
        recipeAPI.add(Recipe(title, instructions, ingredients))
    }

}