package karpat.guvencan.contactapp.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import karpat.guvencan.contactapp.data.common.Constant
import karpat.guvencan.contactapp.data.local.AppDatabase
import karpat.guvencan.contactapp.data.local.Contact
import karpat.guvencan.contactapp.data.local.ContactDao
import karpat.guvencan.contactapp.data.remote.ContactService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface ContactRepository {
    fun dataSourceQuery(query: String?): Flow<PagingData<Contact>>
    suspend fun update(contact: Contact)
    suspend fun delete(contact: Contact)
}

class ContactRepositoryImpl @Inject constructor(
    private val service: ContactService,
    private val db: AppDatabase,
    private val contactDao: ContactDao
) : ContactRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun dataSourceQuery(query: String?): Flow<PagingData<Contact>> = Pager(
        config = PagingConfig(Constant.PAGE_SIZE, enablePlaceholders = true),
        pagingSourceFactory = {
            if (query.isNullOrEmpty()) {
                contactDao.getAll()
            } else {
                contactDao.getAllByQuery(query)
            }
        },
        remoteMediator = RemoteMediator(service, db, contactDao).apply {
            setQuery(query)
        }
    ).flow

    override suspend fun update(contact: Contact) = contactDao.update(contact)
    override suspend fun delete(contact: Contact) = contactDao.clear(contact)

}