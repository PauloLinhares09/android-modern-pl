package com.packapps.network.data.place_detail


import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("business_meeting")
    val businessMeeting: String?,
    @SerializedName("clean")
    val clean: String?,
    @SerializedName("crowded")
    val crowded: String?,
    @SerializedName("dates_popular")
    val datesPopular: String?,
    @SerializedName("dressy")
    val dressy: String?,
    @SerializedName("families_popular")
    val familiesPopular: String?,
    @SerializedName("gluten_free_diet")
    val glutenFreeDiet: String?,
    @SerializedName("good_for_dogs")
    val goodForDogs: String?,
    @SerializedName("groups_popular")
    val groupsPopular: String?,
    @SerializedName("healthy_diet")
    val healthyDiet: String?,
    @SerializedName("late_night")
    val lateNight: String?,
    @SerializedName("noisy")
    val noisy: String?,
    @SerializedName("quick_bite")
    val quickBite: String?,
    @SerializedName("romantic")
    val romantic: String?,
    @SerializedName("service_quality")
    val serviceQuality: String?,
    @SerializedName("singles_popular")
    val singlesPopular: String?,
    @SerializedName("special_occasion")
    val specialOccasion: String?,
    @SerializedName("trendy")
    val trendy: String?,
    @SerializedName("value_for_money")
    val valueForMoney: String?,
    @SerializedName("vegan_diet")
    val veganDiet: String?,
    @SerializedName("vegetarian_diet")
    val vegetarianDiet: String?
)