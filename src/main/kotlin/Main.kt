import controller.RecipeAPI
import models.Recipe
import utils.ScannerInput


private val recipeAPI = RecipeAPI()

/**Shows the menu of recipeApp.
 * Basis for UI
 *@author Niklas Fehringer*/
fun showMenu() : Int {
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

fun exitProgram() {
    println("bye, see you :)")
    kotlin.system.exitProcess(0)
}

fun deleteRecipe() {
    //TODO("Not yet implemented")
    println("deleteRecipe")
}

fun updateRecipe() {
    //TODO("Not yet implemented")
    println("updateRecipe")
}

fun listRecipes() {
    println(recipeAPI.list())
}

fun addRecipe() {
    val title = ScannerInput.readNextLine("Please enter title of recipe: \n")
    val instructions = ScannerInput.readNextLine(("Please enter/copy instructions of recipe in here: \n"))
    //val ingredients = IngredientView.runMenu()
    recipeAPI.add(Recipe(title, instructions))
}

/**Starting point of the program
 * @author Niklas Fehringer*/
fun main() {
    runMenu()
}

