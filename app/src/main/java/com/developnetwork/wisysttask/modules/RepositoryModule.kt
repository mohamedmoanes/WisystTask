package com.developnetwork.wisysttask.modules

import com.developnetwork.wisysttask.data.repositories.ProviderRepo
import com.developnetwork.wisysttask.data.repositories.ProviderRepoImpl
import com.developnetwork.wisysttask.data.repositories.SpecificationsRepo
import com.developnetwork.wisysttask.data.repositories.SpecificationsRepoImpl
import org.koin.dsl.module

val repoModule = module {
    factory<ProviderRepo> { ProviderRepoImpl(service = get()) }
    factory<SpecificationsRepo> { SpecificationsRepoImpl(get()) }
}