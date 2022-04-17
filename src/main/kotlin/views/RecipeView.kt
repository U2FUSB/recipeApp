package views

import models.Recipe
import presenter.RecipeAPI
import utils.ScannerInput

/**
 * Runs the program, shows the Recipe Menu to the User and allows Interaction.
 *
 * @author niklas7U
 * @since V 0
 * @constructor Creates a new instance of RecipeView
 * */
class RecipeView {
    private val recipeAPI = RecipeAPI()
    private val ingredientView = IngredientView()

    /**
     * Shows the Recipe Menu
     * Asks the User to enter a Number
     * @since V 0
     * @return Number entered by User
     * @see [ScannerInput.readNextInt]
     * */
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

    /**
     * runs application.
     * Depending on choice activates different functions allowing:
     * - adding 1
     * - listing 2
     * - updating 3
     * - deleting 4
     * Recipes
     *
     * Also uses a function to stop 0 the application
     * @since V 0
     * @param [menu] type Int: Choice from [showMenu]
     * @see [showMenu]
     *
     * @see [addRecipe]
     * @see [listRecipes]
     * @see [updateRecipe]
     * @see [deleteRecipe]
     * @see [exitProgram]
     * */
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

    /**
     * Stops the application.
     * Any unsaved data will be lost
     *
     * @since V 0
     * @see [kotlin.system.exitProcess]
     * */
    private fun exitProgram() {
        println("bye, see you :)")
        kotlin.system.exitProcess(0)
    }

    /**
     * deletes a recipe, using it's index
     *
     * @since V 0
     * */
    private fun deleteRecipe() {
        val indexToDelete = ScannerInput.readNextInt("Enter the index of the recipe to delete: ")
        val recipeToDelete = recipeAPI.delete(indexToDelete)
        if (recipeToDelete != null) {
            println("Delete Successful! Deleted Recipe: ${recipeToDelete.recipeTitle}")
        } else {
            println("Delete NOT Successful")
        }
    }

    /**
     * updates a recipe, using its index and user input
     *
     * @since V 0
     * */
    private fun updateRecipe() {
        val indexToUpdate = ScannerInput.readNextInt("Enter the index of the Recipe to update:\n")
        val title = ScannerInput.readNextLine("Please enter title of recipe: \n")
        val instructions = ScannerInput.readNextLine(("Please enter/copy instructions of recipe in here: \n"))
        val ingredients = ingredientView.runMenu()
        if (recipeAPI.update(indexToUpdate, Recipe(title, instructions, ingredients))) {
            println("Update Successful")
        } else {
            println("Update Failed")
        }
    }

    /**
     * lists all recipes
     *
     * @since V 0
     **/
    private fun listRecipes() {
        println(recipeAPI.list())
    }

    /**
     * adds a recipe, using user input
     *
     * @since V 0
     **/
    private fun addRecipe() {
        val title = ScannerInput.readNextLine("Please enter title of recipe: \n")
        val instructions = ScannerInput.readNextLine(("Please enter/copy instructions of recipe in here: \n"))
        val ingredients = ingredientView.runMenu()
        recipeAPI.add(Recipe(title, instructions, ingredients))
    }

}