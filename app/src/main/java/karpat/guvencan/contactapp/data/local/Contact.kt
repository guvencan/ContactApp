package karpat.guvencan.contactapp.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "contact",
    indices = [Index(value = ["name", "surname"], unique = false)]
)
data class Contact(
    @PrimaryKey(autoGenerate = true) val pk: Long,
    val id: String? = "",
    val createdAt: String? = null,
    var name: String? = null,
    var surname: String? = null,
    val number: Long? = null,
    var department: String? = null,
    var company_name: String? = null,
    var email: String? = null,
    var nextPageKey: Int? = null,
    var query: String? = null
)