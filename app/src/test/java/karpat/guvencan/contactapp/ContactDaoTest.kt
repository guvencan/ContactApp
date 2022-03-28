package karpat.guvencan.contactapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import karpat.guvencan.contactapp.data.local.AppDatabase
import karpat.guvencan.contactapp.data.local.Contact
import karpat.guvencan.contactapp.data.local.ContactDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ContactDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var appDatabase: AppDatabase

    private lateinit var contactDao: ContactDao


    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        contactDao = appDatabase.contactDao()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun insertUser() = runBlocking {
        val data = Contact(
            pk = 1,
            name = "Güvencan",
            surname = "Karpat"
        )
        contactDao.update(data)
        val allUsers = contactDao.getAllItem()
        assertTrue(allUsers.contains(data))
    }
}

