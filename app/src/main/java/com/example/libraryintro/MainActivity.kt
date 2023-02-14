package com.example.libraryintro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.lazylist.ListActivity
import com.example.lazylist.data.Data
import com.example.libraryintro.databinding.ActivityMainBinding
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
val intentAddr="com."
        val gson: Gson
        gson=Gson()
        val resp: Data = gson.fromJson<Data>("{\n" +
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


        binding.button.setOnClickListener {
            val listActivity:ListActivity
            listActivity=ListActivity()
//            val toLibrary:Intent=Intent(this,LazyActivity)
            startActivity(Intent(this,ListActivity::class.java))

        }
    }
}