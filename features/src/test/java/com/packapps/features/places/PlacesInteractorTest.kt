package com.packapps.features.places

import com.packapps.features.place.model.PlaceDetailViewData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import com.packapps.features.places.model.PlacesRepository
import com.packapps.features.places.model.data.PlaceViewData
import com.packapps.features.places.model.interactor.PlacesInteractor
import com.packapps.network.data.place_detail.Photo
import com.packapps.network.data.place_detail.PlaceDetailResponse
import com.packapps.network.data.place_detail.TipXX
import com.packapps.network.data.places.*
import io.mockk.every
import kotlinx.coroutines.flow.flowOf

class PlacesInteractorTest {

    private lateinit var repository: PlacesRepository
    private lateinit var interactor: PlacesInteractor

    @Before
    fun setup() {
        repository = mockk()
        interactor = PlacesInteractor(repository)
    }

    @Test
    fun `getPlaces returns expected data`() = runTest {

        val mockResult = mockk<Result>()


        val mockCategory = mockk<Category> {
            every { id } returns 123
            every { name } returns "Categoria Teste"
            every { pluralName } returns "Categorias Teste"
            every { shortName } returns "Cat Teste"


            val mockIcon = mockk<Icon> {
                every { prefix } returns "https://example.com/icon_"
                every { suffix } returns ".png"

            }
            every { icon } returns mockIcon
        }

        every { mockCategory.name } returns "Categoria Teste"
        every { mockResult.categories } returns listOf(mockCategory)

        every { mockResult.chains } returns emptyList()

        every { mockResult.closedBucket } returns "Aberto"

        every { mockResult.distance } returns 100

        every { mockResult.fsqId } returns "1234"

        val mockGeocodes = mockk<Geocodes>()
        val mockMain = mockk<Main>()
        every { mockMain.latitude } returns 40.7128
        every { mockMain.longitude } returns -74.0060
        every { mockGeocodes.main } returns mockMain
        every { mockResult.geocodes } returns mockGeocodes

        every { mockResult.link } returns "https://example.com"

        val mockLocation = mockk<Location>()
        every { mockLocation.formattedAddress } returns "Endereço Teste, Cidade Teste"
        every { mockResult.location } returns mockLocation

        every { mockResult.name } returns "Nome Teste"

        every { mockResult.relatedPlaces } returns RelatedPlaces()

        every { mockResult.timezone } returns "UTC"

        val mockPlaceViewData = mockResult.toPlaceViewData()

        val mockResponse = PlacesResponse(context = null, results = listOf(mockResult))

        coEvery { repository.getPlaces(any(), any(), any(), any()) } returns flowOf(mockResponse)

        val result = interactor.getPlaces(null, false, "ll", 1000).first()

        assertEquals(listOf(mockPlaceViewData), result)
    }



    @Test
    fun `getPlaceDetail returns expected data`() = runTest {
        val mockDetailResponse = mockk<PlaceDetailResponse>()
        val mockPlaceDetailViewData = mockDetailResponse.toPlaceDetailViewData(emptyList(), emptyList())

        // Mock repository response
        coEvery { repository.getPlaceDetail(any()) } returns flowOf(mockDetailResponse)
        coEvery { repository.getPlacePhotos(any()) } returns flowOf(emptyList())
        coEvery { repository.getPlaceTips(any()) } returns flowOf(emptyList())

        val result = interactor.getPlaceDetail("fsq_id").first()

        assertEquals(mockPlaceDetailViewData, result)
    }

    fun Result.toPlaceViewData(): PlaceViewData {
        return PlaceViewData(
            fsqId = this.fsqId,
            id = this.categories?.firstOrNull()?.id,
            venueProfileImage = this.categories?.firstOrNull()?.icon?.let { icon -> "${icon.prefix}bg_64${icon.suffix}" },
            venueName = this.name,
            shortName = this.categories?.firstOrNull()?.shortName,
            priceRange = "No price range available", // Você precisará ajustar isso com base na sua API
            userRating = "No rating available", // E este também
            distance = this.distance
        )
    }


    fun PlaceDetailResponse.toPlaceDetailViewData(photos: List<Photo>, tips: List<TipXX>): PlaceDetailViewData {
        return PlaceDetailViewData(
            venueName = this.name.orEmpty(),
            categories = this.categories?.mapNotNull { it?.name.orEmpty() }.orEmpty(),
            // Presumi que `priceRange` e `userRating` sejam simplificações, pois você precisará ajustá-las conforme sua API
            priceRange = "Category • \$ • ★ 4.5 (200)", // Você precisará ajustar isso com base na sua API
            userRating = 4.5, // E este também
            rateCount = 200, // E este
            // Presumi que `isFavorite` seja algo que você gerencia no app, não vindo diretamente da API
            isFavorite = false,
            phoneNumber = "123-456-7890", // Você precisará ajustar isso com base na sua API
            address = this.location?.formattedAddress.orEmpty(),
            isOpenNow = this.hours?.openNow ?: false,
            photos = photos.map { photo ->
                PlaceDetailViewData.Photo(
                    id = photo.id.orEmpty(),
                    createdAt = photo.createdAt.orEmpty(),
                    prefix = photo.prefix.orEmpty(),
                    suffix = photo.suffix.orEmpty(),
                    width = photo.width!!,
                    height = photo.height!!
                )
            },
            tips = tips.map { tip ->
                PlaceDetailViewData.Tip(
                    id = tip.id.orEmpty(),
                    createdAt = tip.createdAt.orEmpty(),
                    text = tip.text.orEmpty()
                )
            }
        )
    }


    // Adicione mais testes conforme necessário para cobrir diferentes cenários e casos de borda
}
