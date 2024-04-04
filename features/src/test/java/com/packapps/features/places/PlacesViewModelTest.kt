package com.packapps.features.places

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.packapps.core.extensions.getOrAwaitValue
import com.packapps.design.utils.ViewState
import com.packapps.features.places.model.interactor.PlacesInteractor
import org.junit.Test

import com.packapps.features.places.model.data.FilterData
import com.packapps.features.places.model.data.PlaceViewData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Assert.*

@ExperimentalCoroutinesApi
class PlacesViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PlacesViewModel
    private val placesInteractor: PlacesInteractor = mockk()

    @Before
    fun setUp() {
        // Use a main dispatcher específica para testes
        Dispatchers.setMain(Dispatchers.Unconfined)

        viewModel = PlacesViewModel(placesInteractor)
    }

    @Test
    fun `fetchPlace with valid data updates LiveData to Success state`() {
        val fakePlacesList = listOf(
            PlaceViewData("id", 1, "http://url.com.br", "Vanue Name", "Short Name", "R$ 10", "4.5", 1000),
        )

        val filterData = FilterData(2, false, "ll", 1000)

        // Configura o mock do interactor para retornar um fluxo com dados falsos
        coEvery { placesInteractor.getPlaces(any(), any(), any(), any()) } returns flowOf(fakePlacesList)

        // Ativa o método fetchPlace
        viewModel.fetchPlace(filterData)

        // Verifica se o estado é Success e contém os dados corretos
        assertTrue(viewModel.placesStateLiveData.getOrAwaitValue() is ViewState.Success<*>)
        assertEquals(fakePlacesList, (viewModel.placesStateLiveData.getOrAwaitValue() as ViewState.Success<List<PlaceViewData>>).data)
    }

    @Test
    fun `fetchPlace with error updates LiveData to Failure state`() {
        val filterData = FilterData(1, false, "ll", 1000)

        // Configura o mock do interactor para lançar uma exceção
        coEvery { placesInteractor.getPlaces(any(), any(), any(), any()) } throws Exception("Test exception")

        // Ativa o método fetchPlace
        viewModel.fetchPlace(filterData)

        // Verifica se o estado é Failure
        assertTrue(viewModel.placesStateLiveData.getOrAwaitValue() is ViewState.Failure)
    }

    @After
    fun tearDown() {
        // Reseta o main dispatcher para evitar problemas em outros testes
        Dispatchers.resetMain()
    }
}

