package karpat.guvencan.contactapp.ui.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import karpat.guvencan.contactapp.base.BaseViewModel
import karpat.guvencan.contactapp.data.local.Contact
import karpat.guvencan.contactapp.repository.ContactRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: ContactRepository) :
    BaseViewModel() {

    var selectedItem: Contact? = null

    fun save() = viewModelScope.launch {
        selectedItem?.let { repository.update(it) }

    }

    fun delete() = viewModelScope.launch {
        selectedItem?.let { repository.delete(it) }

    }

}