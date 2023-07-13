package com.ewide.test.daryhilmyiswara.ui.gamedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewide.test.daryhilmyiswara.model.FavoriteGameTable
import com.ewide.test.daryhilmyiswara.model.GameDetail
import com.ewide.test.daryhilmyiswara.repository.GameDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val gameDetailRepository: GameDetailRepository
) : ViewModel() {

    private val loadingLiveData = MutableLiveData<Boolean>()
    private val gameDetailLiveData = MutableLiveData<GameDetail?>()
    private val errorLiveData = MutableLiveData<String>()
    private val checkFavoriteLiveData = MutableLiveData<Boolean>()
    private val clickFavoriteLiveData = MutableLiveData<Boolean>()

    private var gameId: String = ""

    fun getLoadingLiveData(): LiveData<Boolean> = loadingLiveData
    fun getGameDetailLiveData(): LiveData<GameDetail?> = gameDetailLiveData
    fun getErrorLiveData(): LiveData<String> = errorLiveData
    fun getCheckFavoriteListLiveData(): LiveData<Boolean> = checkFavoriteLiveData
    fun getClickFavoriteListLiveData(): LiveData<Boolean> = clickFavoriteLiveData

    fun getGameDetail(id: String) {
        viewModelScope.launch {
            loadingLiveData.value = true
            val gameDetail = gameDetailRepository.getGameDetail(id)
            if (gameDetail != null) {
                gameDetailLiveData.value = gameDetail
            } else {
                errorLiveData.value = "Server Error"
            }
            loadingLiveData.value = false
        }
    }

    fun setGameId(id: String) {
        gameId = id
    }

    fun getGameId(): String = gameId

    fun getSelectedGame(gameId: String) {
        viewModelScope.launch {
            val result = gameDetailRepository.getFavoriteMovie(gameId)
            checkFavoriteLiveData.value = result != null
        }
    }

    fun insertFavoriteGame(favoriteTable: FavoriteGameTable) {
        viewModelScope.launch {
            gameDetailRepository.insertFavoriteGame(favoriteTable)
            checkFavoriteLiveData.value = true
            clickFavoriteLiveData.value = true
        }
    }

    fun deleteFavoriteGame(gameId: String) {
        viewModelScope.launch {
            gameDetailRepository.deleteFavoriteGame(gameId)
            checkFavoriteLiveData.value = false
            clickFavoriteLiveData.value = false
        }
    }

}