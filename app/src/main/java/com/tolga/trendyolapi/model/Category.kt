package com.tolga.trendyolapi.model

data class Category(
    val id: Int,
    val name: String,
    val parentId: Any,
    val subCategories: List<SubCategory>
)