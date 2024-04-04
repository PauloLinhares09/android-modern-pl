package com.packapps.features.places
import com.packapps.features.places.model.PlacesApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import com.packapps.network.data.places.PlacesResponse
import com.packapps.features.places.model.PlacesRepository
import com.packapps.network.data.place_detail.Photo
import com.packapps.network.data.place_detail.PlaceDetailResponse
import com.packapps.network.data.place_detail.TipXX
import retrofit2.Response

class PlacesRepositoryTest {

    private lateinit var apiService: PlacesApiService
    private lateinit var repository: PlacesRepository

    @Before
    fun setup() {
        apiService = mockk()
        repository = PlacesRepository(apiService)
    }

    @Test
    fun `getPlaces returns expected data`() = runTest {
        val mockResponse = mockk<PlacesResponse>()
        // Mock API response
        coEvery { apiService.getPlaces(any(), any(), any(), any()) } returns Response.success(mockResponse)

        val result = repository.getPlaces(null, false, "ll", 1000).toList().first()

        assertEquals(mockResponse, result)
    }

    @Test
    fun `getPlaceDetail returns expected data`() = runTest {
        val mockResponse = mockk<PlaceDetailResponse>()
        // Mock API response
        coEvery { apiService.getPlaceDetails(any()) } returns Response.success(mockResponse)

        val result = repository.getPlaceDetail("fsq_id").toList().first()

        assertEquals(mockResponse, result)
    }

    @Test
    fun `getPlacePhotos returns expected data`() = runTest {
        val mockPhotosList = listOf(mockk<Photo>())
        // Mock API response
        coEvery { apiService.getPlacePhotos(any()) } returns Response.success(mockPhotosList)

        val result = repository.getPlacePhotos("fsq_id").toList().first()

        assertEquals(mockPhotosList, result)
    }

    @Test
    fun `getPlaceTips returns expected data`() = runTest {
        val mockTipsList = listOf(mockk<TipXX>())
        // Mock API response
        coEvery { apiService.getPlaceTips(any()) } returns Response.success(mockTipsList)

        val result = repository.getPlaceTips("fsq_id").toList().first()

        assertEquals(mockTipsList, result)
    }

    // Add more tests here to cover different scenarios, like API failures.
}
