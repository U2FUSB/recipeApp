package views

import models.Ingredient
import models.Recipe
import persistence.XMLSerializer
import presenter.RecipeAPI
import utils.IndexChecker
import utils.ScannerInput
import java.io.File

/**
 * Runs the program, shows the Recipe Menu to the User and allows Interaction.
 *
 * @author niklas7U
 * @since V 0
 * @constructor Creates a new instance of RecipeView
 * */
class RecipeView {
    private val recipeAPI = RecipeAPI(XMLSerializer(File("recipes.xml")))
    //private val recipeAPI = RecipeAPI(JSONSerializer(File("recipes.json")))

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
        > ---------------------------
        > | RECIPE MANAGEMENT APP   |
        > ---------------------------
        > | RECIPE MENU             |
        > |   1) Add a recipe       |
        > |   2) List recipes       |
        > |   3) Update a recipe    |
        > |   4) Delete a recipe    |
        > ---------------------------
        > |   10) Save recipes      |
        > |   11) Load recipes      |
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
                10 -> saveRecipes()
                11 -> loadRecipes()
                -99 -> createTestRecipe()
                else -> println("Option $menu is invalid. Try another one")
            }
        } while (true)
    }

    private fun loadRecipes() {
        try {
            recipeAPI.load()
        } catch (e: Exception) {
            System.err.println("Error reading to file: $e")
        }
    }

    private fun saveRecipes() {
        try {
            recipeAPI.store()
        } catch (e:Exception) {
            System.err.println("Error writing to file: $e")
        }
    }

    private fun createTestRecipe() {
        recipeAPI.add(
            Recipe(
                "Ham and eggs",
                "Step 1\n" +
                        "Beat eggs, milk, seasoned salt, salt, and black pepper together in a bowl.\n" +
                        "\n" +
                        "Step 2\n" +
                        "Heat olive oil in a large non-stick skillet over medium-high heat; saute jalapeno in hot oil until slightly softened, 2 to 3 minutes. Add ham to jalapeno and cook until heated through, about 1 minute.\n" +
                        "\n" +
                        "Step 3\n" +
                        "Pour egg mixture into ham mixture. Cook and stir until eggs are set but not dry, 3 to 5 minutes. Sprinkle 1/2 of the Cheddar cheese over eggs; cook and stir until cheese is melted. Transfer eggs to a plate and sprinkle remaining cheese over the top.",
                arrayListOf(
                    Ingredient("egg", 8, "piece"),
                    Ingredient("milk", 3, "tablespoons"),
                    Ingredient("salt", 1, "teespoon"),
                    Ingredient("olive oil", 1, "cup"),
                    Ingredient("Jalapeno", 1, "piece"),
                    Ingredient("applewood-smoked ham", 1, "cup"),
                    Ingredient("Cheddar cheese", 1, "cup")
                )
            )
        )
        recipeAPI.add(
            Recipe("Buttersoup", "1. Melt Butter till melted", arrayListOf(Ingredient("Butter", 500, "g")))
        )
        recipeAPI.add(
            Recipe("testRecipe", "this is not really a recipe, but whatever", arrayListOf(Ingredient("noHam", 240, "kg")))
        )
        println(recipeAPI.list())
        println("\n\nTest recipes added")
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
        if (recipeAPI.numberOfRecipes() > 0) {
            println(recipeAPI.listAllNames())
            val indexToDelete = ScannerInput.readNextInt("Enter the index of the recipe to delete: ")
            if (IndexChecker.isValidIndex(indexToDelete, recipeAPI.get())) {
                val recipeToDelete = recipeAPI.delete(indexToDelete)
                if (recipeToDelete != null) {
                    println("Delete Successful! Deleted Recipe: ${recipeToDelete.recipeTitle}")
                } else {
                    println("Delete NOT Successful")
                }
            } else {
                println("Index does not exist. Delete Failed")
            }
        } else {
            println("no recipes stored yet")
        }
    }

    /**
     * updates a recipe, using its index and user input
     *
     * @since V 0
     * */
    private fun updateRecipe() {
        println(recipeAPI.listAllNames())
        val indexToUpdate = ScannerInput.readNextInt("Enter the index of the Recipe to update:\n")
        if (IndexChecker.isValidIndex(indexToUpdate, recipeAPI.get())) {
            val title = ScannerInput.readNextLine("Please enter title of recipe: \n")
            val instructions = ScannerInput.readNextLine(("Please enter/copy instructions of recipe in here: \n"))
            val ingredients = IngredientView().runMenu(recipeAPI.findRecipe(indexToUpdate)?.recipeIngredients)
            if (recipeAPI.update(indexToUpdate, Recipe(title, instructions, ingredients))) {
                println("Update Successful")
            } else {
                println("Update Failed")
            }
        } else {
            println("Index does not exist. Update Failed")
        }
    }

    /**
     * lists all recipes
     *
     * @since V 0
     **/
    private fun listRecipes() {
        if (recipeAPI.numberOfRecipes() > 0) {
            val option = ScannerInput.readNextInt(
                """
                  > --------------------------------
                  > |   1) View ALL recipes        |
                  > |   2) View A SPECIFIC recipe  |
                  > --------------------------------
         > ==>> """.trimMargin(">"))
            when (option) {
                1 -> println(recipeAPI.list())
                2 -> {
                    println(recipeAPI.listAllNames())
                    val indexToList = ScannerInput.readNextInt("\nEnter the index of desired recipe: ")
                    if (IndexChecker.isValidIndex(indexToList, recipeAPI.get())) {
                        println(recipeAPI.list(indexToList))
                    } else {
                        println("Invalid index")
                    }
                }
                else -> println("Invalid option entered: $option")
            }
        } else {
            println("no recipes stored yet")
        }
    }

    /**
     * adds a recipe, using user input
     *
     * @since V 0
     **/
    private fun addRecipe() {
        val title = ScannerInput.readNextLine("Please enter title of recipe: \n")
        val instructions = ScannerInput.readAllNextLines("Please enter/copy instructions of recipe in here, ")
        val ingredients = IngredientView().runMenu()
        recipeAPI.add(Recipe(title, instructions, ingredients))
    }

}