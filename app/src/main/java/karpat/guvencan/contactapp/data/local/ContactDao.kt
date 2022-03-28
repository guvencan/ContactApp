package karpat.guvencan.contactapp.data.local

import androidx.paging.PagingSource
import androidx.room.*


@Dao
interface ContactDao {
    @Query("SELECT * FROM contact")
    fun getAll(): PagingSource<Int, Contact>

    @Query("SELECT * FROM contact WHERE query = :query")
    fun getAllByQuery(query: String): PagingSource<Int, Contact>

    @Query("SELECT * FROM contact")
    fun getAllItem(): List<Contact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Contact>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact)

    @Query("DELETE FROM contact")
    suspend fun clearAll()

    @Delete
    suspend fun clear(alert: Contact)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(user: Contact)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAll(user: List<Contact>)


}