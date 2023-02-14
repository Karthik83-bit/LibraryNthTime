package com.example.lazylist

import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lazylist.data.Data
import com.example.lazylist.data.Weather
import com.example.lazylist.databinding.ListViewBinding
import com.google.gson.Gson

class ListActivity: AppCompatActivity() {
    lateinit var binding:ListViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Data





        val gson:Gson
        gson=Gson()
       val resp:Data= gson.fromJson<Data>("{\n" +
                "            \"sys\": {\n" +
                "            \"country\": \"GB\",\n" +
                "            \"sunrise\": 1381107633,\n" +
                "            \"sunset\": 1381149604\n" +
                "        },\n" +
                "            \"weather\": [{\n" +
                "            \"id\": 711,\n" +
                "            \"main\": \"Smoke\",\n" +
                "            \"description\": \"smoke\",\n" +
                "            \"icon\": \"50n\"\n" +
                "        }],\n" +
                "            \"main\": {\n" +
                "            \"temp\": 304.15,\n" +
                "            \"pressure\": 1009\n" +
                "        }\n" +
                "        }",Data::class.java)
        Log.d("msg","${resp}")
        binding.Country.text="Country:"+resp.sys.country
        binding.sunrise.text=resp.sys.sunrise.toString()
        binding.sunset.text=resp.sys.sunset.toString()
        binding.textView8.text="Temp:${resp.main.temp.toString()}"
        binding.textView9.text="Pressure:${resp.main.pressure.toString()}"
        var list:ArrayList<Weather>
        list= ArrayList()
        list= resp.weather as ArrayList<Weather>
//        val adapter=ArrayAdapter(this,list,)
55
        val recyclerView=findViewById<RecyclerView>(R.id.rv)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val rvAdapter=RVAdapter(list)
        recyclerView.adapter=rvAdapter





    }
}