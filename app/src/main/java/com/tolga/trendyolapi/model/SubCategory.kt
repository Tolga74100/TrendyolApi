package com.tolga.trendyolapi.model

data class SubCategory(
    val id: Int,
    val name: String,
    val parentId: Int,
    val subCategories: List<SubCategoryX>
)