package com.moanes.wisysttask.modules

import com.moanes.wisysttask.data.repositories.ProviderRepo
import com.moanes.wisysttask.data.repositories.ProviderRepoImpl
import com.moanes.wisysttask.data.repositories.SpecificationsRepo
import com.moanes.wisysttask.data.repositories.SpecificationsRepoImpl
import org.koin.dsl.module

val repoModule = module {
    factory<ProviderRepo> { ProviderRepoImpl(service = get()) }
    factory<SpecificationsRepo> { SpecificationsRepoImpl(get()) }
}