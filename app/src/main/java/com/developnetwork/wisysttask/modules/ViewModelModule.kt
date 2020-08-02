package com.developnetwork.wisysttask.modules

import com.developnetwork.wisysttask.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }

}