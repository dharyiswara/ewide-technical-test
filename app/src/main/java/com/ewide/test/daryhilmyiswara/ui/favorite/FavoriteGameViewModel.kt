package com.ewide.test.daryhilmyiswara.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewide.test.daryhilmyiswara.model.FavoriteGameTable
import com.ewide.test.daryhilmyiswara.repository.FavoriteGameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteGameViewModel @Inject constructor(
    private val favoriteGameRepository: FavoriteGameRepository
) : ViewModel() {

    private val loadingLiveData = MutableLiveData<Boolean>()
    private val favoriteGameLiveData = MutableLiveData<List<FavoriteGameTable>>()

    fun getLoadingLiveData(): LiveData<Boolean> = loadingLiveData
    fun getFavoriteGameLiveData(): LiveData<List<FavoriteGameTable>> = favoriteGameLiveData

    fun getFavoriteGames() {
        viewModelScope.launch {
            loadingLiveData.value = true
            favoriteGameLiveData.value = favoriteGameRepository.getFavoriteGames()
            loadingLiveData.value = false
        }
    }

}