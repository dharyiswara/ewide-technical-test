package com.ewide.test.daryhilmyiswara.ui.gameslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewide.test.daryhilmyiswara.model.Games
import com.ewide.test.daryhilmyiswara.repository.GamesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesListViewModel @Inject constructor(
    private val gamesListRepository: GamesListRepository
) : ViewModel() {

    private val loadingLiveData = MutableLiveData<Boolean>()
    private val gamesListLiveData = MutableLiveData<List<Games>>()
    private val errorLiveData = MutableLiveData<String>()

    fun getLoadingLiveData(): LiveData<Boolean> = loadingLiveData
    fun getGamesListLiveData(): LiveData<List<Games>> = gamesListLiveData
    fun getErrorLiveData(): LiveData<String> = errorLiveData

    fun getGamesList(title: String = "batman", limit: Int = 10) {
        viewModelScope.launch {
            loadingLiveData.value = true
            val listGames = gamesListRepository.getGamesList(title, limit)
            if (listGames != null) {
                gamesListLiveData.value = listGames.orEmpty()
            } else {
                errorLiveData.value = "Server Error"
            }
            loadingLiveData.value = false
        }
    }

    fun setIsSortAscending(isSortAscending: Boolean) {
        gamesListRepository.setIsSortAscending(isSortAscending)
    }

    fun isSortAscending(): Boolean = gamesListRepository.isSortAscending()

}