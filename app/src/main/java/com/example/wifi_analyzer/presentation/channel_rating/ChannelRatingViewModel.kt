package com.example.wifi_analyzer.presentation.channel_rating

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wifi_analyzer.domain.usecases.GetChannelRatingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChannelRatingViewModel @Inject constructor(
    private val getChannelRatingsUseCase: GetChannelRatingsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChannelRatingUiState())
    val uiState: StateFlow<ChannelRatingUiState> = _uiState.asStateFlow()

    init {
        loadRatings()
    }

    fun loadRatings() {
        viewModelScope.launch {
            _uiState.value = ChannelRatingUiState(isLoading = true)
            getChannelRatingsUseCase().collect { list ->
                _uiState.value = ChannelRatingUiState(ratings = list, isLoading = false)
            }
        }
    }
}