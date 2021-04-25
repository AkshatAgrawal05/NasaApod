package gov.nasa.apod.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gov.nasa.apod.data.model.ResPotd
import gov.nasa.apod.data.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val potdLiveData = MutableLiveData<ResPotd>()
    val potd: LiveData<ResPotd>
        get() = potdLiveData

    init {
        viewModelScope.launch {
            val response = repository.getPotd()
            potdLiveData.value = response
        }
    }
}