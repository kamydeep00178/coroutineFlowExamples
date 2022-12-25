package com.usupgradeskills.coroutineflowexamples.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.usupgradeskills.coroutineflowexamples.repo.CFlowRepo
import java.lang.IllegalArgumentException

class CFlowViewModelFactory constructor(private val repo: CFlowRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CFlowViewModel::class.java)){
            CFlowViewModel(this.repo) as T
        }
        else{
            throw IllegalArgumentException("View Model is not found")
        }
    }
}