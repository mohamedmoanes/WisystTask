package com.moanes.wisysttask.modules

import com.moanes.wisysttask.ui.home.HomeViewModel
import com.moanes.wisysttask.ui.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(),get()) }
    viewModel { MapViewModel(get()) }

}