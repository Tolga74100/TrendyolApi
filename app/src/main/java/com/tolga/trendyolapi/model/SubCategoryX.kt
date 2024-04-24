package com.tolga.trendyolapi.model

data class SubCategoryX(
    val id: Int,
    val name: String,
    val parentId: Int,
    val subCategories: List<SubCategoryXX>
)