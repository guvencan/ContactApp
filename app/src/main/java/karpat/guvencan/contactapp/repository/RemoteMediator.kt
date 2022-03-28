package karpat.guvencan.contactapp.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import karpat.guvencan.contactapp.data.local.AppDatabase
import karpat.guvencan.contactapp.data.local.Contact
import karpat.guvencan.contactapp.data.local.ContactDao
import karpat.guvencan.contactapp.data.remote.ContactService
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RemoteMediator @Inject constructor(
    private val service: ContactService,
    private val db: AppDatabase,
    private val contactDao: ContactDao
) : RemoteMediator<Int, Contact>() {

    private var query: String? = null

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Contact>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                state.pages.lastOrNull()?.data?.lastOrNull()?.nextPageKey
                    ?: return MediatorResult.Success(endOfPaginationReached = true)
            }
        }

        try {
            val response = service.getContact(page, state.config.pageSize, query)
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    val isEndOfList = data.isNullOrEmpty()
                    db.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            contactDao.clearAll()
                        }
                        data.map {
                            it.nextPageKey = page + 1
                            it.query = query
                        }.also {
                            contactDao.insertAll(data)
                        }

                    }
                    return MediatorResult.Success(endOfPaginationReached = isEndOfList)
                }
            }
            return MediatorResult.Error(Exception("Network problem"))
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    fun setQuery(s: String?) {
        query = s
    }

}