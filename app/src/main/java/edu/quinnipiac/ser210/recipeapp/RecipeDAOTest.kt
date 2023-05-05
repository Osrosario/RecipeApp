import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.quinnipiac.ser210.recipeapp.Recipe
import org.junit.runner.RunWith

import edu.quinnipiac.ser210.recipeapp.RecipeDAO
import edu.quinnipiac.ser210.recipeapp.RecipeDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RecipeDAOTest
{
    private lateinit var recipeDAO: RecipeDAO
    private lateinit var recipeDatabase: RecipeDatabase
    private var recipe1 = Recipe(1, "Chicken", "1 Serving","Chicken", "Cook")
    private var recipe2 = Recipe(2, "Pancake", "1 Serving","Pancake Mix", "Bake")

    @Before
    fun createDb()
    {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        recipeDatabase = Room.inMemoryDatabaseBuilder(context, RecipeDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        recipeDAO = recipeDatabase.recipeDAO()
    }

    private suspend fun addOneItemToDb()
    {
        recipeDAO.insert(recipe1)
    }

    private suspend fun addTwoItemsToDb()
    {
        recipeDAO.insert(recipe1)
        recipeDAO.insert(recipe2)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsItemIntoDB() = runBlocking {
        addOneItemToDb()
        val allItem = recipeDAO.getAll().first()
        Assert.assertEquals(allItem, recipe1)
    }
    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnsAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = recipeDAO.getAll()
        Assert.assertEquals(allItems[0], recipe1)
        Assert.assertEquals(allItems[1], recipe2)
    }
    @After
    @Throws(IOException::class)
    fun closeDb()
    {
        recipeDatabase.close()
    }

}