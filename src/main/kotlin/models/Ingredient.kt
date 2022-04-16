
package models

/**
 * [ingredientName] Name of ingredient
 * [ingredientAmount] Amount as Integer
 * [ingredientUnit] Unit for the amount of the ingredient. G by standard.
 *
 * @author niklas7U
 * @since V 0
 * @constructor Creates a new instance of Ingredient
 * */
data class Ingredient(
    var ingredientName: String,
    var ingredientAmount: Int,
    var ingredientUnit: String = "g"
)
