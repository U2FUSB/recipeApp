package presenter

import models.Ingredient
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IngredientAPITest {

    private var populatedIngredients: IngredientAPI? = IngredientAPI()
    private var emptyIngredients: IngredientAPI? = IngredientAPI()

    private var ham: Ingredient? = null
    private var egg: Ingredient? = null
    private var milk: Ingredient? = null
    private var potatos: Ingredient? = null
    private var spaghetti: Ingredient? = null

    @BeforeEach
    fun setup() {
        // Ingredients
        ham = Ingredient("ham", 250, "g")
        egg = Ingredient("egg", 3, "pieces")
        milk = Ingredient("milk", 1, "l")

        // RecipeLists
        populatedIngredients!!.add(ham!!)
        populatedIngredients!!.add(egg!!)
        populatedIngredients!!.add(milk!!)
    }

    @AfterEach
    fun teardown() {
        ham = null
        egg = null
        milk = null
        potatos = null
        spaghetti = null

        populatedIngredients = null
        emptyIngredients = null
    }

    @Nested
    inner class AddIngredients {
        @Test
        fun `adding an Ingredient to a populated list adds to ArrayList`() {
            val newIngredient = Ingredient("Butter", 5, "g" )
            populatedIngredients!!.add(newIngredient)
            assertTrue(populatedIngredients!!.get().toString().contains("Butter"))
        }
    }

    @Nested
    inner class AddAllIngredients {
        @Test
        fun `adding multiple Ingredients to a populated list adds them to the ArrayList`() {
            val butter = Ingredient("Butter", 5, "g" )
            val onion = Ingredient("Onion", 3, "piece" )
            val garlic = Ingredient("Garlic", 2, "piece" )
            populatedIngredients!!.addAll(arrayListOf<Ingredient>(butter, onion, garlic))
            assertTrue(populatedIngredients!!.list().contains("Butter"))
            assertTrue(populatedIngredients!!.list().contains("Onion"))
            assertTrue(populatedIngredients!!.list().contains("Garlic"))
        }
    }

    @Nested
    inner class GetIngredients {
        @Test
        fun `Getting the Arraylist of Ingredients from a populated list contains it's elements`() {
            assertTrue(populatedIngredients!!.get().toString().contains("ham"))
            assertTrue(populatedIngredients!!.get().toString().contains("3"))
            assertTrue(populatedIngredients!!.get().toString().contains("l"))
        }
    }

    @Nested
    inner class ListIngredients {
        @Test
        fun `listing all Ingredients from a populated list contains it's elements`() {
            assertTrue(populatedIngredients!!.list().contains("ham"))
            assertTrue(populatedIngredients!!.list().contains("3"))
            assertTrue(populatedIngredients!!.list().contains("l"))
        }
    }

    @Nested
    inner class UpdateIngredients {
        @Test
        fun `updating ingredients updates elements in arraylist`() {
            populatedIngredients!!.update(0, Ingredient("noHam", 240, "kg"))
            assertTrue(populatedIngredients!!.get().toString().contains("noHam"))
        }
    }
    @Nested
    inner class DeleteIngredients {
        @Test
        fun `deleting an ingredient returns it`() {
            assertEquals(populatedIngredients!!.delete(0), ham)
        }
        @Test
        fun `deleting an ingredient removes it form the arraylist`() {
            populatedIngredients!!.delete(0)
            assertFalse(populatedIngredients!!.list().contains("ham"))
        }
    }

    @Nested
    inner class CountingMethods {
        @Test
        fun `counting ingredients of populated list returns actual size`() {
            assertEquals(populatedIngredients?.numberOfIngredients(), 3)
        }
        @Test
        fun `counting ingredients of empty list returns actual size`() {
            assertEquals(emptyIngredients?.numberOfIngredients(), 0)
        }
    }
}