package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class CreditCards(
    @SerializedName("accepts_credit_cards")
    val acceptsCreditCards: AcceptsCreditCards?,
    @SerializedName("amex")
    val amex: Amex?,
    @SerializedName("diners_club")
    val dinersClub: DinersClub?,
    @SerializedName("discover")
    val discover: Discover?,
    @SerializedName("master_card")
    val masterCard: MasterCard?,
    @SerializedName("union_pay")
    val unionPay: UnionPay?,
    @SerializedName("visa")
    val visa: Visa?
)