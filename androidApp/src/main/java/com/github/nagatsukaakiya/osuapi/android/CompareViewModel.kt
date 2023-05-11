package com.github.nagatsukaakiya.osuapi.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CompareViewModel(
    private val userRepository: UserRepository,
): ViewModel() {
    val myTopPPScores: StateFlow<List<Float>>
        get() = _myTopPPScores
    private val _myTopPPScores: MutableStateFlow<List<Float>> = MutableStateFlow(emptyList())

    fun getMyTopPPScores() {
        viewModelScope.launch {
            _myTopPPScores.value = userRepository.getMyBestScores().map { it.pp }
        }
    }
}
