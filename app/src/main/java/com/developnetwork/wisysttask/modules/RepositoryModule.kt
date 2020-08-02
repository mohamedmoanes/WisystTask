package com.developnetwork.wisysttask.modules

import com.developnetwork.wisysttask.data.repositories.ProviderRepoImpl
import com.developnetwork.wisysttask.data.repositories.SpecificationsRepoImpl
import org.koin.dsl.module

val repoModule = module {
    factory { ProviderRepoImpl(get()) }
    factory { SpecificationsRepoImpl(get()) }
}