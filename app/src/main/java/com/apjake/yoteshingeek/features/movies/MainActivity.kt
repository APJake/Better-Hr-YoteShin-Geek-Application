package com.apjake.yoteshingeek.features.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.apjake.yoteshingeek.R
import com.apjake.yoteshingeek.YoteShinApplication
import com.apjake.yoteshingeek.databinding.ActivityMainBinding
import com.apjake.yoteshingeek.di.AppComponent
import com.apjake.yoteshingeek.features.movies.home.HomeViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}