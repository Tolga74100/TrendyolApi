package com.tolga.trendyolapi.model

data class SubCategoryXXX(
    val id: Int,
    val name: String,
    val parentId: Int,
    val subCategories: List<SubCategoryXXXX>
)