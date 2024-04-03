package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class Payment(
    @SerializedName("credit_cards")
    val creditCards: CreditCards?,
    @SerializedName("digital_wallet")
    val digitalWallet: DigitalWallet?
)