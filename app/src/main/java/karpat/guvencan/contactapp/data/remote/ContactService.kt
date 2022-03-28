package karpat.guvencan.contactapp.data.remote

import karpat.guvencan.contactapp.data.local.Contact
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ContactService {

    @GET("contacts")
    suspend fun getContact(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("search") word: String? = null,
    ): Response<List<Contact>>

}
