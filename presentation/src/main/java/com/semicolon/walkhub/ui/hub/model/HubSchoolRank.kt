package com.semicolon.walkhub.ui.hub.model

data class HubSchoolRank(
    val my_school_rank : MySchool,
    val school_list : School
)

data class MySchool(
    val school_id: Int,
    val name: String,
    val logo_image_url: String,
    val walk_count: Int,
    val grade: Int,
    val class_num: Int
)

data class School(
    val school_id: Int,
    val name: String,
    val ranking: Int,
    val student_count: Int,
    val logo_image_url: String,
    val walk_count: Int
)