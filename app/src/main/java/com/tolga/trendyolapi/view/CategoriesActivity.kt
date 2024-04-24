package com.tolga.trendyolapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tolga.trendyolapi.R
import com.tolga.trendyolapi.databinding.ActivityCategoriesBinding
import com.tolga.trendyolapi.model.Category
import com.tolga.trendyolapi.model.TrendyolCategoriesModel
import com.tolga.trendyolapi.service.TrendyolApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoriesActivity : AppCompatActivity() {
    private lateinit var categoryBinding: ActivityCategoriesBinding
    private val BASE_URL_CATEGORİES = "https://api.trendyol.com/"
    private var trendyolCategoriesModel:ArrayList<Category>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryBinding = ActivityCategoriesBinding.inflate(layoutInflater)
        val view = categoryBinding.root
        setContentView(view)
        loadCategoryData()
    }

    private fun loadCategoryData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_CATEGORİES)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(TrendyolApi::class.java)
        val call = service.getCategoriesData()

        call.enqueue(object : Callback<TrendyolCategoriesModel>{
            override fun onResponse(
                call: Call<TrendyolCategoriesModel>,
                response: Response<TrendyolCategoriesModel>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        trendyolCategoriesModel = ArrayList(it.categories)


                        for (trendyolCategoriesModel : Category in trendyolCategoriesModel!!){
                        println(trendyolCategoriesModel.id)
                        println(trendyolCategoriesModel.name)
                            println(trendyolCategoriesModel.parentId)
                           println(trendyolCategoriesModel.subCategories)


                    }
                    }
                }

            }

            override fun onFailure(call: Call<TrendyolCategoriesModel>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}