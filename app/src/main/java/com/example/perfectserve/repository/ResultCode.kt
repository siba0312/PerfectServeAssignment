package com.bolt.interview.repository

import java.io.Serializable

enum class ResultCode(val id: Int, val msg: String) : Serializable {
    NETWORK_NOT_AVAILABLE(1, "The Network Not Availbale"),
    DEFAULT_ERROR(2, "Something went wrong, please try again!"),
    SEARCH_TERM_ERROR(3,"Could not execute search, try specifying a more exact location or term.");
}