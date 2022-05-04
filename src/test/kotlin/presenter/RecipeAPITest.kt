package presenter

import models.Ingredient
import models.Recipe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.JSONSerializer
import persistence.XMLSerializer
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RecipeAPITest {

    private var populatedRecipes: RecipeAPI? = RecipeAPI(XMLSerializer(File("recipes.xml")))
    private var emptyRecipes: RecipeAPI? = RecipeAPI(XMLSerializer(File("recipes.xml")))

    private var hamAndEggs: Recipe<Any?>? = null
    private var bread: Recipe<Any?>? = null
    private var cookies: Recipe<Any?>? = null
    private var shepherdsPie: Recipe<Any?>? = null
    private var spaghettiCarbonara: Recipe<Any?>? = null

    @BeforeEach
    fun setup() {
        // Recipes
        hamAndEggs = Recipe(
            "Learning Kotlin",
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
        // RecipeLists
        populatedRecipes!!.add(hamAndEggs)
    }

    @AfterEach
    fun teardown() {
        hamAndEggs = null
        bread = null
        cookies = null
        shepherdsPie = null
        spaghettiCarbonara = null

        populatedRecipes = null
        emptyRecipes = null
    }

    @Nested
    inner class AddRecipes {
        @Test
        fun `adding a Recipe to a populated list adds to ArrayList`() {
            val newRecipe = Recipe<Any?>("Buttersoup", "1. Melt Butter till melted", arrayListOf(Ingredient("Butter", 500, "g")))
            populatedRecipes!!.add(newRecipe)
            assertTrue(populatedRecipes!!.list().contains("Buttersoup"))
        }
    }
    @Nested
    inner class ListRecipes {
        @Test
        fun `listing all Recipes from a populated list contains it's elements`() {
            assertTrue(populatedRecipes!!.list().contains("Beat eggs, milk, seasoned salt, salt, and black pepper together in a bowl."))
            assertTrue(populatedRecipes!!.list().contains("Step 3"))
            assertTrue(populatedRecipes!!.list().contains("salt"))
        }
        @Test
        fun `listing names of all recipes just returns their names`() {
            assertTrue(populatedRecipes!!.listAllNames().contains("Kotlin"))
            assertFalse(populatedRecipes!!.listAllNames().contains("Step 1"))
        }
    }
    @Nested
    inner class UpdateRecipes {
        @Test
        fun `updating recipes updates elements in arraylist`() {
            populatedRecipes!!.update(0, Recipe("testRecipe", "this is not really a recipe, but whatever", arrayListOf(Ingredient("noHam", 240, "kg"))))
            assertTrue(populatedRecipes!!.list().contains("not really a recipe"))
        }
    }
    @Nested
    inner class DeleteRecipes {
        @Test
        fun `deleting an recipe returns it`() {
            assertEquals(populatedRecipes!!.delete(0), hamAndEggs)
        }
        @Test
        fun `deleting an recipe removes it form the arraylist`() {
            populatedRecipes!!.delete(0)
            assertFalse(populatedRecipes!!.list().contains("a large non-stick skillet over medium-high heat"))
        }
    }
    @Nested
    inner class FindRecipe {
        @Test
        fun `finding an recipe by its index returns it`() {
            assertEquals(populatedRecipes!!.findRecipe(0), hamAndEggs)
        }
    }

    @Nested
    inner class GetRecipes {
        @Test
        fun `Getting the Arraylist of Ingredients from a populated list contains it's elements`() {
            assertTrue(populatedRecipes!!.get().toString().contains("eggs"))
            assertTrue(populatedRecipes!!.get().toString().contains("olive oil"))
            assertTrue(populatedRecipes!!.get().toString().contains("Cheddar"))
        }
    }
    @Nested
    inner class CountingMethods {
        @Test
        fun `counting recipes of populated list returns actual size`() {
            assertEquals(populatedRecipes?.numberOfRecipes(), 1)
        }
        @Test
        fun `counting recipes of empty list returns actual size`() {
            assertEquals(emptyRecipes?.numberOfRecipes(), 0)
        }
    }
    @Nested
    inner class PersistenceTests {
        @Test
        fun `saving and loading an loaded collection in JSON doesn't loose data`() {

            val storingRecipes = RecipeAPI(JSONSerializer(File("recipes.json")))
            storingRecipes.add(hamAndEggs!!)
            storingRecipes.store()


            val loadedRecipes = RecipeAPI(JSONSerializer(File("recipes.json")))
            loadedRecipes.load()


            assertEquals(1, storingRecipes.numberOfRecipes())
            assertEquals(1, loadedRecipes.numberOfRecipes())
            assertEquals(storingRecipes.numberOfRecipes(), loadedRecipes.numberOfRecipes())
            assertEquals(storingRecipes.findRecipe(0), loadedRecipes.findRecipe(0))
        }
        @Test
        fun `saving and loading an loaded collection in XML doesn't loose data`() {

            val storingRecipes = RecipeAPI(XMLSerializer(File("recipes.json")))
            storingRecipes.add(hamAndEggs!!)
            storingRecipes.store()


            val loadedRecipes = RecipeAPI(XMLSerializer(File("recipes.json")))
            loadedRecipes.load()


            assertEquals(1, storingRecipes.numberOfRecipes())
            assertEquals(1, loadedRecipes.numberOfRecipes())
            assertEquals(storingRecipes.numberOfRecipes(), loadedRecipes.numberOfRecipes())
            assertEquals(storingRecipes.findRecipe(0), loadedRecipes.findRecipe(0))
        }
    }

    @Nested
    inner class FindRecipesContainingIngredients {
        @Test
        fun `finding Recipes by Ingredients returns all desired recipes` () {
            assertTrue(populatedRecipes?.findIngredient("salt").toString().contains("Learning Kotlin"))
        }
    }
}