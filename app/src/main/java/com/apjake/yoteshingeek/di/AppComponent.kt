package com.apjake.yoteshingeek.di

import android.content.Context
import com.apjake.yoteshingeek.features.movies.MainActivity
import com.apjake.yoteshingeek.features.movies.home.HomeFragment
import com.apjake.yoteshingeek.features.movies.movie_detail.MovieDetailFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NetworkModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: HomeFragment)
    fun inject(fragment: MovieDetailFragment)
}