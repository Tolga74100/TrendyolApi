package com.tolga.trendyolapi.service

import com.tolga.trendyolapi.model.Brand
import com.tolga.trendyolapi.model.TrendyolBrandsModel
import com.tolga.trendyolapi.model.TrendyolCategoriesModel
import io.reactivex.Observable
import io.reactivex.Observer

import retrofit2.Call
import retrofit2.http.GET

interface TrendyolApi {

    //https://api.trendyol.com/sapigw/product-categories
    //https://api.trendyol.com/sapigw/brands

    @GET("sapigw/brands")
    fun getBrandsData(): Observable<TrendyolBrandsModel>
    //fun getBrandsData():Call<TrendyolBrandsModel>
    @GET("sapigw/product-categories")
    //fun getCategiesData(): Observable<TrendyolCategoriesModel>
    fun getCategoriesData(): Call<TrendyolCategoriesModel>
}