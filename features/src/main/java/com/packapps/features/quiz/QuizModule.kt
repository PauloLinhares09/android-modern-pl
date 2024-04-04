package com.packapps.features.quiz

import com.packapps.features.quiz.viewModel.QuizViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val quizModule = module {
    viewModel { QuizViewModel() }
}