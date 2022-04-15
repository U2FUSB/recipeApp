import utils.ScannerInput

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
            2 -> listRecipe()
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

fun listRecipe() {
    //TODO("Not yet implemented")
    println("listRecipe")
}

fun addRecipe() {
    //TODO("Not yet implemented")
    println("addRecipe")
}

/**Starting point of the program
 * @author Niklas Fehringer*/
fun main() {
    runMenu()
}

