package com.packapps.features.place.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlaceDetailViewData(
    val venueName: String,
    val categories: List<String>, // Para simplicidade, vamos usar uma lista de nomes de categorias
    val priceRange: String? = null, // A API de exemplo não fornece faixa de preço, então este campo é opcional
    val userRating: Double? = null, // A API de exemplo não fornece avaliação do usuário, então este campo é opcional
    val rateCount: Int? = null, // A API de exemplo não fornece contagem de avaliações, então este campo é opcional
    val isFavorite: Boolean = false, // Deve ser controlado na UI ou lógica de negócios
    // A API de exemplo não fornece número de telefone diretamente, considerando opcional
    val phoneNumber: String? = null,
    val address: String,
    val isOpenNow: Boolean, // Usaremos 'closed_bucket' para determinar a disponibilidade
    val photos: List<Photo>, // A API de exemplo não fornece fotos, então este campo pode precisar ser ajustado
    val tips: List<Tip> // A API de exemplo não fornece dicas, então este campo pode precisar ser ajustado
) : Parcelable {

    @Parcelize
    data class Photo(
        val id: String,
        val createdAt: String,
        val prefix: String,
        val suffix: String,
        val width: Int,
        val height: Int
    ) : Parcelable

    @Parcelize
    data class Tip(
        val id: String,
        val createdAt: String,
        val text: String
    ) : Parcelable
}
