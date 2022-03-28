package karpat.guvencan.contactapp.ui.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import karpat.guvencan.contactapp.base.BaseViewModel
import karpat.guvencan.contactapp.repository.ContactRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ContactRepository
) : BaseViewModel() {

    fun getData(query: String? = null) = repository.dataSourceQuery(query).cachedIn(viewModelScope)

}