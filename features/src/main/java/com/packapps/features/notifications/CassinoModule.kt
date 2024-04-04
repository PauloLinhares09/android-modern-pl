package com.packapps.features.notifications

import com.packapps.features.notifications.viewModel.CassinoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cassinoModule = module {
    viewModel { CassinoViewModel() }
}