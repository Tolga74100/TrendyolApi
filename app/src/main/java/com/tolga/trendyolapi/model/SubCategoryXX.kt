package com.tolga.trendyolapi.model

data class SubCategoryXX(
    val id: Int,
    val name: String,
    val parentId: Int,
    val subCategories: List<SubCategoryXXX>
)