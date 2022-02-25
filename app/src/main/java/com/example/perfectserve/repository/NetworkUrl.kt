package com.bolt.interview.repository

object NetworkUrl {
    fun getBusinessSearchUrl(queryParam: String): String {
        val BASE_URL = "https://api.yelp.com/v3/businesses/search?location="
        return BASE_URL.plus(queryParam)
    }
}