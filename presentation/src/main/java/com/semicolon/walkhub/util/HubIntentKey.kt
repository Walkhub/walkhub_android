package com.semicolon.walkhub.util

enum class HubIntentKey(val key: String, val default: Any) {
    SCHOOL_ID(key = "school_id", default = 0),
    SCHOOL_TYPE(key = "school_type", default = true),
    SCHOOL_NAME(key = "school_name", default = "NO DATA"),
    SCHOOL_DATE_TYPE(key = "school_date_type", default = "WEEK")
}