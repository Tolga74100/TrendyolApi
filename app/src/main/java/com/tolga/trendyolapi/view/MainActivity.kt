package com.tolga.trendyolapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tolga.trendyolapi.R
import com.tolga.trendyolapi.adapter.RecyclerViewAdapter
import com.tolga.trendyolapi.databinding.ActivityMainBinding
import com.tolga.trendyolapi.model.Brand
import com.tolga.trendyolapi.model.TrendyolBrandsModel
import com.tolga.trendyolapi.service.TrendyolApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RecyclerViewAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://api.trendyol.com/"
   private var trendyolBrandsModels: ArrayList<Brand>? = null
    private  var recyclerViewAdapter: RecyclerViewAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager =layoutManager
        loadBrandsData()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.trendyol_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.trendyol_category_chose){
            val intent = Intent(this@MainActivity,CategoriesActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadBrandsData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(TrendyolApi::class.java)
        val call = service.getBrandsData()

        call.enqueue(object : Callback<TrendyolBrandsModel>{
            override fun onResponse(
                call: Call<TrendyolBrandsModel>,
                response: Response<TrendyolBrandsModel>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        trendyolBrandsModels = ArrayList(it.brands)

                        trendyolBrandsModels?.let {
                            recyclerViewAdapter = RecyclerViewAdapter(it,this@MainActivity)
                            binding.recyclerView.adapter= recyclerViewAdapter
                        }



                        /*for (trendyolModel : Brand in trendyolModels!!){
                            println(trendyolModel.id)
                            println(trendyolModel.name)


                        }*/
                    }
                }
            }

            override fun onFailure(call: Call<TrendyolBrandsModel>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    override fun onItemClick(brand: Brand) {
        Toast.makeText(this,"Clicked : ${brand.name}", Toast.LENGTH_LONG ).show()
    }
}